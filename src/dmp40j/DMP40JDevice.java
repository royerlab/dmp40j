package dmp40j;

import dmp40j.bindings.TLDFMX_64Library;
import dmp40j.bindings.TLDFM_64Library;
import org.bridj.CLong;
import org.bridj.Pointer;

import static dmp40j.DMP40J.MAX_SEGMENTS;
import static dmp40j.DMP40J.VI_FIND_BUFLEN;
import static dmp40j.DMP40J.translateStatus;
import static dmp40j.bindings.TLDFMX_64Library.*;
import static dmp40j.bindings.TLDFM_64Library.TLDFM_BUFFER_SIZE;
import static dmp40j.bindings.TLDFM_64Library.TLDFM_MAX_INSTR_NAME_LENGTH;
import static dmp40j.bindings.TLDFM_64Library.TLDFM_MAX_SN_LENGTH;
import static dmp40j.bindings.TLDFM_64Library.TLDFM_init;
import static dmp40j.bindings.TLDFM_64Library.VI_NULL;
import static dmp40j.bindings.TLDFM_64Library.VI_TRUE;

/**
 * DMP40JDevice
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 05 2018
 */
public class DMP40JDevice implements AutoCloseable {

    private Object lock = new Object();

    private String serialNumber;

    private Integer instrumentHandle = null;
    private double minZernikeAmplitude;
    private double maxZernikeAmplitude;
    private int zernikeFactorCount;
    private int systemMeasurementSteps;
    private int relaxSteps;


    private int numberOfSegments;
    private int numberOfTiltElements;
    private double voltageMirrorMin;
    private double voltageMirrorMax;
    private double voltageMirrorCommon;
    private double voltageTiltMin;
    private double voltageTiltMax;
    private double voltageTiltCommon;
    private Integer instrumentExtensionHandle;

    private double[] currentMirrorShape = new double[MAX_SEGMENTS];


    public DMP40JDevice(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean open() {

        synchronized (lock) {
            if (instrumentHandle != null) {
                return false;
            }
            // ---------------------------------------------------------------
            // read number of devices
            Pointer<CLong> lInstrumentCount = Pointer.allocateCLong();
            long err = TLDFM_64Library.TLDFM_get_device_count(VI_NULL, lInstrumentCount);
            System.out.println("\nReading number of devices status: " + translateStatus(err));
            System.out.println("Number of devices: " + lInstrumentCount.getInt());


            // ---------------------------------------------------------------
            // read infos of devices
            Pointer<Byte> manufacturer = Pointer.allocateBytes(TLDFM_64Library.TLDFM_BUFFER_SIZE);
            Pointer<Byte> instrumentName = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_INSTR_NAME_LENGTH);
            Pointer<Byte> serialNumber = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_SN_LENGTH);
            Pointer<Short> deviceAvailable = Pointer.allocateShort();
            Pointer<Byte> resourceName = Pointer.allocateBytes(VI_FIND_BUFLEN);

            int deviceIndex = -1;

            for (int i = 0; i < lInstrumentCount.getInt(); i++) {

                err = TLDFM_64Library.TLDFM_get_device_information(VI_NULL,
                        i,
                        manufacturer,
                        instrumentName,
                        serialNumber,
                        deviceAvailable,
                        resourceName);

                System.out.println("\nRead device information status: " + translateStatus(err));

                System.out.println("device index:" + i + "\n" +
                        "manufacturer: " + manufacturer.getCString() + "\n" +
                        "instrumentname: " + instrumentName.getCString() + "\n" +
                        "serial number: " + serialNumber.getCString() + "\n" +
                        (deviceAvailable.getShort() != 0 ? "available" : "locked") + "\n" +
                        "resource: " + resourceName.getCString());

                if (serialNumber.getCString().contains(this.serialNumber)) {
                    System.out.println("Device " + this.serialNumber + " identified!");
                    deviceIndex = i;
                    break;
                }
            }
            manufacturer.release();
            instrumentName.release();
            serialNumber.release();

            // ---------------------------------------------------------------
            // connect to the mirror
            if (deviceAvailable.getShort() != 0 && deviceIndex >= 0) {
                Pointer<CLong> instrumentHandlePointer = Pointer.allocateCLong();
                err = TLDFM_64Library.TLDFM_init(resourceName, (short) VI_TRUE, (short) VI_TRUE, instrumentHandlePointer);
                System.out.println("\nConnecting to " + resourceName.getCString() + " status: " + translateStatus(err));
                System.out.println("Handle: " + instrumentHandlePointer.getInt());
                instrumentHandle = instrumentHandlePointer.getInt();

                err = TLDFMX_init(resourceName, (short)VI_TRUE, (short)VI_TRUE, instrumentHandlePointer);
                System.out.println("\nConnecting to " + resourceName.getCString() + " status: " + translateStatus(err));
                System.out.println("Handle: " + instrumentHandlePointer.getInt());
                instrumentExtensionHandle = instrumentHandlePointer.getInt();

                // Get extension driver parameters
                Pointer<CLong> zernikeCountPointer = Pointer.allocateCLong();
                Pointer<CLong> systemMeasurementStepsPointer = Pointer.allocateCLong();
                Pointer<CLong> relaxStepsPointer = Pointer.allocateCLong();
                Pointer<Double> minZernikeAmplitudePointer = Pointer.allocateDouble();
                Pointer<Double> maxZernikeAmplitudePointer = Pointer.allocateDouble();

                err = TLDFMX_get_parameters(instrumentExtensionHandle,
                        minZernikeAmplitudePointer,
                        maxZernikeAmplitudePointer,
                        zernikeCountPointer,
                        systemMeasurementStepsPointer,
                        relaxStepsPointer);
                System.out.println("\nGet parameters status: " + translateStatus(err));

                System.out.println("Zernike Amplitude        : " + minZernikeAmplitudePointer.getDouble() + " - " + maxZernikeAmplitudePointer.getDouble());
                System.out.println("Zernike Amount           : " +  zernikeCountPointer.getInt());
                System.out.println("System Measurement Steps : " +  systemMeasurementStepsPointer.getInt());
                System.out.println("Relax Steps              : " + relaxStepsPointer.getInt());

                minZernikeAmplitude = minZernikeAmplitudePointer.getDouble();
                maxZernikeAmplitude = maxZernikeAmplitudePointer.getDouble();
                zernikeFactorCount = zernikeCountPointer.getInt();
                systemMeasurementSteps = systemMeasurementStepsPointer.getInt();
                relaxSteps = relaxStepsPointer.getInt();





                Pointer<CLong> segmentsPointer = Pointer.allocateCLong();
                Pointer<CLong> tiltElementsPointer = Pointer.allocateCLong();
                Pointer<Double> voltageMirrorMinPointer = Pointer.allocateDouble();
                Pointer<Double> voltageMirrorMaxPointer = Pointer.allocateDouble();
                Pointer<Double> voltageMirrorCommonPointer = Pointer.allocateDouble();
                Pointer<Double> voltageTiltMinPointer = Pointer.allocateDouble();
                Pointer<Double> voltageTiltMaxPointer = Pointer.allocateDouble();
                Pointer<Double> voltageTiltCommonPointer = Pointer.allocateDouble();

                err = TLDFM_64Library.TLDFM_get_device_configuration(instrumentHandle,
                        segmentsPointer,
                        voltageMirrorMinPointer,
                        voltageMirrorMaxPointer,
                        voltageMirrorCommonPointer,
                        tiltElementsPointer,
                        voltageTiltMinPointer,
                        voltageTiltMaxPointer,
                        voltageTiltCommonPointer);

                System.out.println("\nGet device configuration status: " + translateStatus(err));
                System.out.println("Number of Segments        :" + segmentsPointer.getInt());
                System.out.println("Min. Voltage              :" + voltageMirrorMinPointer.getDouble());
                System.out.println("Max. Voltage              :" + voltageMirrorMaxPointer.getDouble());
                System.out.println("No. of Tip/Tilt Elements  :" + tiltElementsPointer.getInt());
                System.out.println("Min. Voltage              :" + voltageTiltMinPointer.getDouble());
                System.out.println("Max. Voltage              :" + voltageMirrorMaxPointer.getDouble());

                numberOfSegments = segmentsPointer.getInt();
                numberOfTiltElements = tiltElementsPointer.getInt();
                voltageMirrorMin = voltageMirrorMinPointer.getDouble();
                voltageMirrorMax = voltageMirrorMaxPointer.getDouble();
                voltageMirrorCommon = voltageMirrorCommonPointer.getDouble();
                voltageTiltMin = voltageTiltMinPointer.getDouble();
                voltageTiltMax = voltageTiltMaxPointer.getDouble();
                voltageTiltCommon = voltageTiltCommonPointer.getDouble();


                segmentsPointer.release();
                tiltElementsPointer.release();
                voltageMirrorMinPointer.release();
                voltageMirrorMaxPointer.release();
                voltageMirrorCommonPointer.release();
                voltageTiltMinPointer.release();
                voltageTiltMaxPointer.release();
                voltageTiltCommonPointer.release();



            } else {
                instrumentHandle = null;
            }

            deviceAvailable.release();
            resourceName.release();
        }
        return instrumentHandle != null;
    }

    @Override
    public void close() throws Exception {
        synchronized (lock) {
            if (instrumentHandle != null) {
                long err = TLDFM_64Library.TLDFM_close(instrumentHandle);
                System.out.println("\nClose device status: " + translateStatus(err));
                instrumentHandle = null;

                err = TLDFMX_64Library.TLDFMX_close(instrumentExtensionHandle);
                System.out.println("\nClose extension status: " + translateStatus(err));
                instrumentExtensionHandle = null;

            }
        }
    }

    public double getMinZernikeAmplitude() {
        return minZernikeAmplitude;
    }

    public double getMaxZernikeAmplitude() {
        return maxZernikeAmplitude;
    }

    public int getNumberOfZernikeFactors() {
        return zernikeFactorCount;
    }

    public double getVoltageMirrorMin() {
        return voltageMirrorMin;
    }

    public double getVoltageMirrorMax() {
        return voltageMirrorMax;
    }

    public double getVoltageMirrorCommon() {
        return voltageMirrorCommon;
    }

    public double getVoltageTiltMin() {
        return voltageTiltMin;
    }

    public double getVoltageTiltMax() {
        return voltageTiltMax;
    }

    public double getVoltageTiltCommon() {
        return voltageTiltCommon;
    }

    public int getNumberOfSegments() {
        return numberOfSegments;
    }

    public int getNumberOfTiltElements() {
        return numberOfTiltElements;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getZernikeFactorCount() {
        return zernikeFactorCount;
    }

    public int getRelaxSteps() {
        return relaxSteps;
    }

    public int getSystemMeasurementSteps() {
        return systemMeasurementSteps;
    }

    public boolean isOpen() {
        return instrumentHandle != null;
    }

    public boolean sendFlatMirrorShapeVector()
    {
        double[] zernikeFactors = new double[zernikeFactorCount];
        return setZernikeFactors(zernikeFactors);
    }

    public boolean setRawMirrorShapeVector(double[] actuatorVoltages)
    {
        Pointer<Double> segmentVoltagesPointer = Pointer.allocateDoubles(MAX_SEGMENTS);
        doubleArrayToDoublePointer(actuatorVoltages, segmentVoltagesPointer);
        boolean result = setRawMirrorShapeVector(segmentVoltagesPointer);
        segmentVoltagesPointer.release();
        return result;
    }

    private static void doubleArrayToDoublePointer(double[] array, Pointer<Double> pointer) {
        for (int i = 0; i < array.length && i < MAX_SEGMENTS; i++) {
            pointer.set(i, array[i]);
        }
    }

    private static void doublePointerToDoubleArray(Pointer<Double> pointer, double[] array) {
        for (int i = 0; i < array.length && i < MAX_SEGMENTS; i++) {
            array[i] = pointer.get(i);
        }
    }


    private boolean setRawMirrorShapeVector(Pointer<Double> segmentVoltages) {
        synchronized (lock) {

            long err = TLDFM_64Library.TLDFM_set_segment_voltages(instrumentHandle, segmentVoltages);
            System.out.println("\nSet voltages status: " + translateStatus(err));
            if (err == 0) {
                doublePointerToDoubleArray(segmentVoltages, currentMirrorShape);
            }
            return err == 0;
        }
    }

    public boolean setZernikeFactors(double[] zernikeFactors) {
        Pointer<Double> zernikeFactorsPointer = Pointer.allocateDoubles(zernikeFactorCount);
        doubleArrayToDoublePointer(zernikeFactors, zernikeFactorsPointer);

        Pointer<Double> segmentVoltagesPointer = Pointer.allocateDoubles(MAX_SEGMENTS);
        synchronized (lock) {
            // documentation located here:
            long err = TLDFMX_calculate_zernike_pattern(instrumentExtensionHandle, 0xFFFFFFFF, zernikeFactorsPointer, segmentVoltagesPointer);
            System.out.println("\nCalculate zernike pattern status: " + translateStatus(err));
        }
        boolean result = setRawMirrorShapeVector(segmentVoltagesPointer);
        segmentVoltagesPointer.release();
        zernikeFactorsPointer.release();

        return result;
    }


    public boolean setSingleZernikeFactor(TLDFMX_64Library.TLDFMX_zernike_flag_t flag, double zernikeFactor) {
        Pointer<Double> segmentVoltagesPointer = Pointer.allocateDoubles(MAX_SEGMENTS);
        synchronized (lock) {

            long err = TLDFMX_calculate_single_zernike_pattern(instrumentExtensionHandle,
                    flag,
                    zernikeFactor,
                    segmentVoltagesPointer);
            System.out.println("\nCalculate single zernike pattern status: " + translateStatus(err));
        }
        boolean result = setRawMirrorShapeVector(segmentVoltagesPointer);
        segmentVoltagesPointer.release();

        return result;
    }

    public boolean setTilt(double tiltAmplitude, double tiltAngle) {
        synchronized (lock) {
            long err = TLDFM_64Library.TLDFM_set_tilt_amplitude_angle(instrumentHandle, tiltAmplitude, tiltAngle);
            System.out.println("\nSet tilt status: " + translateStatus(err));

            return err == VI_SUCCESS;
        }
    }

    public double[] getMirrorShape() {
        double[] result = new double[currentMirrorShape.length];
        System.arraycopy(currentMirrorShape, 0, result, 0, result.length);
        return result;
    }
}

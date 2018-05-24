package dmp40j.bindings.demo;

import org.bridj.CLong;
import org.bridj.Pointer;

import static dmp40j.DMP40J.MAX_SEGMENTS;
import static dmp40j.DMP40J.VI_FIND_BUFLEN;
import static dmp40j.DMP40J.translateStatus;
import static dmp40j.bindings.TLDFM_64Library.*;

/**
 * TLDFM_64LibraryDemo
 *
 * Demo code for how to talk to Thorlabs deformable mirror DMP40/M-P01
 *
 * Adapted from Thorlabs example code located in
 * C:\Program Files (x86)\IVI Foundation\VISA\WinNT\TLDFM\Examples\CSample\sample.c
 *
 * Author: @haesleinhuepf
 * 05 2018
 */
public class TLDFM_64LibraryDemo {



    public static void main(String... args) {

        // ---------------------------------------------------------------
        // read number of devices
        Pointer<CLong> lInstrumentCount = Pointer.allocateCLong();
        long err = TLDFM_get_device_count(VI_NULL, lInstrumentCount);
        System.out.println("\nReading number of devices status: " + translateStatus(err));
        System.out.println("Number of devices: " + lInstrumentCount.getInt());


        // ---------------------------------------------------------------
        // read infos of devices
        Pointer<Byte> manufacturer = Pointer.allocateBytes(TLDFM_BUFFER_SIZE);
        Pointer<Byte> instrumentName = Pointer.allocateBytes(TLDFM_MAX_INSTR_NAME_LENGTH);
        Pointer<Byte> serialNumber = Pointer.allocateBytes(TLDFM_MAX_SN_LENGTH);
        Pointer<Short> deviceAvailable = Pointer.allocateShort();
        Pointer<Byte> resourceName = Pointer.allocateBytes(VI_FIND_BUFLEN);

        for (int i = 0; i < lInstrumentCount.getInt(); i++) {

            err = TLDFM_get_device_information(VI_NULL,
                    i,
                    manufacturer,
                    instrumentName,
                    serialNumber,
                    deviceAvailable,
                    resourceName);

            System.out.println("\nRead device information status: " + translateStatus(err) );

            System.out.println("device index:" + i + "\n" +
                    "manufacturer: " + manufacturer.getCString() + "\n" +
                    "instrumentname: " + instrumentName.getCString()  + "\n" +
                    "serial number: " + serialNumber.getCString()  + "\n" +
                    (deviceAvailable.getShort() != 0 ? "available" : "locked") + "\n" +
                    "resource: " + resourceName.getCString());
        }
        manufacturer.release();
        instrumentName.release();
        serialNumber.release();
        deviceAvailable.release();

        // ---------------------------------------------------------------
        // connect to the last mirror

        Pointer<CLong> instrumentHandle = Pointer.allocateCLong();
        err = TLDFM_init(resourceName, (short)VI_TRUE, (short)VI_TRUE, instrumentHandle);
        System.out.println("\nConnecting to " + resourceName.getCString() + " status: " + translateStatus(err));
        System.out.println("Handle: " + instrumentHandle.getInt());

        resourceName.release();

        // ---------------------------------------------------------------
        // read device info

        Pointer<CLong> segments = Pointer.allocateCLong();
        Pointer<CLong> tiltElements = Pointer.allocateCLong();
        Pointer<Double> voltageMirrorMin = Pointer.allocateDouble();
        Pointer<Double> voltageMirrorMax = Pointer.allocateDouble();
        Pointer<Double> voltageMirrorCommon = Pointer.allocateDouble();
        Pointer<Double> voltageTiltMin = Pointer.allocateDouble();
        Pointer<Double> voltageTiltMax = Pointer.allocateDouble();
        Pointer<Double> voltageTiltCommon = Pointer.allocateDouble();

        err = TLDFM_get_device_configuration(instrumentHandle.getInt(),
                segments,
                voltageMirrorMin,
                voltageMirrorMax,
                voltageMirrorCommon,
                tiltElements,
                voltageTiltMin,
                voltageTiltMax,
                voltageTiltCommon);

        System.out.println("\nGet device configuration status: " + translateStatus(err));
        System.out.println("Number of Segments        :" + segments.getInt());
        System.out.println("Min. Voltage              :" + voltageMirrorMin.getDouble());
        System.out.println("Max. Voltage              :" + voltageMirrorMax.getDouble());
        System.out.println("No. of Tip/Tilt Elements  :" + tiltElements.getInt());
        System.out.println("Min. Voltage              :" + voltageTiltMin.getDouble());
        System.out.println("Max. Voltage              :" + voltageMirrorMax.getDouble());

        segments.release();
        tiltElements.release();
        voltageMirrorMin.release();
        voltageMirrorMax.release();
        voltageMirrorCommon.release();
        voltageTiltMin.release();
        voltageTiltMax.release();
        voltageTiltCommon.release();

        // ---------------------------------------------------------------
        // Reset Device: All Voltages = 0.0V; Hysteresis = On
        err = TLDFM_reset(instrumentHandle.getInt());
        System.out.println("\nReset device, all voltages=0, Hysteresis=On status: " + translateStatus(err));

        // ---------------------------------------------------------------
        // set voltages to some value
        Pointer<Double> segmentVoltages = Pointer.allocateDoubles(MAX_SEGMENTS);
        for (int i = 0; i < MAX_SEGMENTS; i++) {
            segmentVoltages.set(i, voltageMirrorMin.getDouble() +
                    i * (voltageMirrorMax.getDouble() - voltageMirrorMin.getDouble()) / (MAX_SEGMENTS - 1));
        }

        err = TLDFM_set_segment_voltages(instrumentHandle.getInt(), segmentVoltages);
        System.out.println("\nSet voltages status: " + translateStatus(err));

        segmentVoltages.release();

        // ---------------------------------------------------------------
        // Adjust Mirror Tip/Tilt
        double tiltAmplitude = 1.0; // max.
        System.out.println("TiltAmplitude = " + tiltAmplitude);

        double tiltAngle = 0;
        for(double angle = -180.0; angle <= 180.0; angle += 30.0)
        {
            tiltAngle = angle;

            err = TLDFM_set_tilt_amplitude_angle (instrumentHandle.getInt(), tiltAmplitude, tiltAngle);
            System.out.println("\nSet tilt status: " + translateStatus(err));

            System.out.println("TiltAngle = " + tiltAngle + " deg");
        }


        // ---------------------------------------------------------------
        // Read Feedback Input
        Pointer<Double> feedbackVoltage = Pointer.allocateDouble();
        Pointer<Double> feedbackCurrent = Pointer.allocateDouble();
        Pointer<Double> monitorVoltage = Pointer.allocateDouble();

        err = TLDFM_get_feedback (instrumentHandle.getInt(), feedbackVoltage, feedbackCurrent);
        System.out.println("\nGet feedback status: " + translateStatus(err));

        System.out.println("Feedback Voltage: " + feedbackVoltage.getDouble() + "V");
        System.out.println("Feedback Current: " + feedbackCurrent.getDouble() + "uA");

        err = TLDFM_get_monitor_voltage(instrumentHandle.getInt(), monitorVoltage);
        System.out.println("\nGet mnitor voltage status: " + translateStatus(err));

        System.out.println("Monitor Voltage: " + monitorVoltage.getDouble() + "V");

        feedbackVoltage.release();
        feedbackCurrent.release();
        monitorVoltage.release();

        // close device
        err = TLDFM_close (instrumentHandle.getInt());
        System.out.println("Close device status: " + translateStatus(err));

        instrumentHandle.release();
    }
}

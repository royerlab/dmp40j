package dmp40j.bindings.demo;

import dmp40j.bindings.TLDFM_64Library;
import org.bridj.CLong;
import org.bridj.Pointer;
import org.junit.experimental.theories.PotentialAssignment;

import java.awt.*;
import java.util.Arrays;

import static dmp40j.DMP40J.MAX_SEGMENTS;
import static dmp40j.DMP40J.VI_FIND_BUFLEN;
import static dmp40j.DMP40J.translateStatus;
import static dmp40j.bindings.TLDFMX_64Library.*;
import static dmp40j.bindings.TLDFMX_64Library.TLDFMX_zernike_flag_t.Z_Def_Flag;
import static dmp40j.bindings.TLDFM_64Library.*;
import static dmp40j.bindings.TLDFM_64Library.VI_NULL;
import static dmp40j.bindings.TLDFM_64Library.VI_TRUE;

/**
 * TLDFMX_64LibraryDemo
 *
 * Adapted from Thorlabs example code:
 * C:\Program Files (x86)\IVI Foundation\VISA\WinNT\TLDFMX\Examples\CSample\TLDFMX_C_Sample.c
 *
 * Author: @haesleinhuepf
 * 05 2018
 */
public class TLDFMX_64LibraryDemo {


    public static void main(String... args) {

        int deviceIndex = 0;

        // ---------------------------------------------------------------
        // read infos of devices
        Pointer<Byte> manufacturer = Pointer.allocateBytes(TLDFM_64Library.TLDFM_BUFFER_SIZE);
        Pointer<Byte> instrumentName = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_INSTR_NAME_LENGTH);
        Pointer<Byte> serialNumber = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_SN_LENGTH);
        Pointer<Short> deviceAvailable = Pointer.allocateShort();
        Pointer<Byte> resourceName = Pointer.allocateBytes(VI_FIND_BUFLEN);



        long err = TLDFM_64Library.TLDFM_get_device_information(VI_NULL,
                    deviceIndex,
                    manufacturer,
                    instrumentName,
                    serialNumber,
                    deviceAvailable,
                    resourceName);

        System.out.println("\nRead device information status: " + translateStatus(err) );

        System.out.println("device index:" + deviceIndex + "\n" +
                "manufacturer: " + manufacturer.getCString() + "\n" +
                "instrumentname: " + instrumentName.getCString()  + "\n" +
                "serial number: " + serialNumber.getCString()  + "\n" +
                (deviceAvailable.getShort() != 0 ? "available" : "locked") + "\n" +
                "resource: " + resourceName.getCString());

        manufacturer.release();
        instrumentName.release();
        serialNumber.release();
        deviceAvailable.release();

        // ---------------------------------------------------------------
        // connect to the last mirror

        Pointer<CLong> instrumentHandle = Pointer.allocateCLong();
        err = TLDFMX_init(resourceName, (short)VI_TRUE, (short)VI_TRUE, instrumentHandle);
        System.out.println("\nConnecting to " + resourceName.getCString() + " status: " + translateStatus(err));
        System.out.println("Handle: " + instrumentHandle.getInt());

        resourceName.release();



        // Get instrument info
        Pointer<Byte> manufNameBuf = Pointer.allocateBytes(TLDFM_64Library.TLDFM_BUFFER_SIZE);
        Pointer<Byte> instrNameBuf = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_INSTR_NAME_LENGTH);
        Pointer<Byte> snBuf = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_SN_LENGTH);
        Pointer<Byte> drvRevBuf = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_STRING_LENGTH);
        Pointer<Byte> fwRevBuf = Pointer.allocateBytes(TLDFM_64Library.TLDFM_MAX_STRING_LENGTH);

        err = TLDFM_64Library.TLDFM_get_manufacturer_name (instrumentHandle.getInt(), manufNameBuf);
        System.out.println("\nGet manufacturer status: " + translateStatus(err));
        System.out.println("Manufacturer: " + manufNameBuf.getCString());

        err = TLDFM_64Library.TLDFM_get_instrument_name (instrumentHandle.getInt(), instrNameBuf);
        System.out.println("\nGet instrument name status: " + translateStatus(err));
        System.out.println("Instrument name: " + instrNameBuf.getCString());

        err = TLDFM_64Library.TLDFM_get_serial_Number (instrumentHandle.getInt(), snBuf);
        System.out.println("\nGet serial status: " + translateStatus(err));
        System.out.println("Serial: " + snBuf.getCString());

        err = TLDFMX_revision_query (instrumentHandle.getInt(), drvRevBuf, fwRevBuf);
        System.out.println("\nGet revision status: " + translateStatus(err));
        System.out.println("Driver revision: " + drvRevBuf.getCString());
        System.out.println("Firmware revision: " + fwRevBuf.getCString());

        manufNameBuf.release();
        instrNameBuf.release();
        snBuf.release();
        drvRevBuf.release();
        fwRevBuf.release();


        // Get extension driver parameters
        Pointer<CLong> zernikeCount = Pointer.allocateCLong();
        Pointer<CLong> systemMeasurementSteps = Pointer.allocateCLong();
        Pointer<CLong> relaxSteps = Pointer.allocateCLong();
        Pointer<Double> minZernikeAmplitude = Pointer.allocateDouble();
        Pointer<Double> maxZernikeAmplitude = Pointer.allocateDouble();

        err = TLDFMX_get_parameters(instrumentHandle.getInt(),
                minZernikeAmplitude,
								maxZernikeAmplitude,
								zernikeCount,
								systemMeasurementSteps,
								relaxSteps);
        System.out.println("\nGet parameters status: " + translateStatus(err));

        System.out.println("Zernike Amplitude        : " + minZernikeAmplitude.getDouble() + " - " + maxZernikeAmplitude.getDouble());
        System.out.println("Zernike Amount           : " +  zernikeCount.getInt());
        System.out.println("System Measurement Steps : " +  systemMeasurementSteps.getInt());
        System.out.println("Relax Steps              : " + relaxSteps.getInt());


        long zernikeFactorCount = zernikeCount.getInt();

                zernikeCount.release();
        systemMeasurementSteps.release();
        relaxSteps.release();
        minZernikeAmplitude.release();
        maxZernikeAmplitude.release();




        // set a defocus and calcate acutator pattern
        TLDFMX_zernike_flag_t zernike = Z_Def_Flag;
        double zernikeAmplitude = 0.1;
        Pointer<Double> zernikePattern = Pointer.allocateDoubles(MAX_SEGMENTS);


        // Calculate voltage pattern
        err = TLDFMX_calculate_single_zernike_pattern(instrumentHandle.getInt(),
                zernike,
                zernikeAmplitude,
                zernikePattern);


        //Pointer<Double> zernikeFactors = Pointer.allocateDoubles(zernikeFactorCount);
        //zernikeFactors.set(zernike.value() + 3, zernikeAmplitude);

        //err = TLDFMX_calculate_zernike_pattern(instrumentHandle.getInt(), zernikeFactorCount, zernikeFactors, zernikePattern);
        System.out.println("\nCalculate single zernike pattern status: " + translateStatus(err));

        //zernikeFactors.release();


        for(int i = 0; i < MAX_SEGMENTS; i++)
        {
            if((0 == i) || (8 == i) || (24 == i))
            {
                System.out.println("   Ring " + (0 == i ? 1 : 8 == i ? 2 : 3) + " -> " + zernikePattern.get(i));
            }
            else if((7 == i) || (23 == i))
            {
                System.out.println(" ; " +  zernikePattern.get(i) + "\n");
            }
            else
            {
                System.out.println(" ; " + zernikePattern.get(i));
            }
        }

        // Set voltages to device, the pattern is already range checked
        // [range checking is enabled by default]
        err = TLDFM_64Library.TLDFM_set_segment_voltages(instrumentHandle.getInt(), zernikePattern);
        System.out.println("\nSet segment voltages status: " + translateStatus(err));

        zernikePattern.release();



        // close the device
        err = TLDFMX_reset(instrumentHandle.getInt());
        System.out.println("\nReset status: " + translateStatus(err));

        err = TLDFMX_close(instrumentHandle.getInt());
        System.out.println("\nClose status: " + translateStatus(err));

    }

}

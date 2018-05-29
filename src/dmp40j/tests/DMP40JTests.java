package dmp40j.tests;

import dmp40j.DMP40J;
import dmp40j.DMP40JDevice;
import org.junit.Test;

import static dmp40j.bindings.TLDFMX_64Library.TLDFMX_zernike_flag_t.*;
import static dmp40j.bindings.TLDFMX_64Library.TLDFMX_zernike_flag_t.Z_ComX_Flag;
import static dmp40j.bindings.TLDFMX_64Library.TLDFMX_zernike_flag_t.Z_ComY_Flag;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * DMP40JTests
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 05 2018
 */
public class DMP40JTests {

    private final static double tolerance = 0.0001;

    @Test
    public void testSingleAndMultipleZernikesDoTheSame() {
        DMP40JDevice mirror = new DMP40JDevice("M00456037");

        if (mirror.open()) {

            System.out.println("The mirror has serial number " + mirror.getSerialNumber());
            System.out.println("This mirror supports " + mirror.getNumberOfZernikeFactors() + " zernike factors.");
            System.out.println("Zernike factors should range between " + mirror.getMinZernikeAmplitude() + " and " + mirror.getMaxZernikeAmplitude());

            double[] zernikeFactors = new double[mirror.getNumberOfZernikeFactors()];
            zernikeFactors[DMP40J.TLDFMX_Z4_AMPL_POS] = mirror.getMinZernikeAmplitude();
            mirror.setZernikeFactors(zernikeFactors);
            double[] multipleZernikesMirrorShape = mirror.getMirrorShape();

            mirror.setSingleZernikeFactor(Z_Def_Flag, mirror.getMinZernikeAmplitude());
            double[] singleZernikesMirrorShape = mirror.getMirrorShape();

            for(int i = 0; i < singleZernikesMirrorShape.length; i++) {
                assertEquals(singleZernikesMirrorShape[i], multipleZernikesMirrorShape[i], tolerance);
            }
        } else {
            System.out.println("Could not connect to mirror " + mirror.getSerialNumber());
        }

    }
}

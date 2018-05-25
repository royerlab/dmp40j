package dmp40j.demo;

import dmp40j.DMP40JDevice;

import static dmp40j.bindings.TLDFMX_64Library.TLDFMX_zernike_flag_t.*;

/**
 * DMP40JDemo
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 05 2018
 */
public class DMP40JDemo {
    public static void main(String... args) throws Exception {

        DMP40JDevice mirror = new DMP40JDevice("M00456037");

        if (mirror.open()) {

            System.out.println("The mirror has serial number " + mirror.getSerialNumber());
            System.out.println("This mirror supports " + mirror.getNumberOfZernikeFactors() + " zernike factors.");
            System.out.println("Zernike factors should range between " + mirror.getMinZernikeAmplitude() + " and " + mirror.getMaxZernikeAmplitude());

            double[] zernikeFactors = new double[mirror.getNumberOfZernikeFactors()];
            zernikeFactors[(int)Z_Def_Flag.value] = mirror.getMinZernikeAmplitude();
            zernikeFactors[(int)Z_Ast0_Flag.value] = mirror.getMaxZernikeAmplitude();

            mirror.setZernikeFactors(zernikeFactors);

            Thread.sleep(1000);

            for (int j = 0 ; j < 100; j++) {
                for (int i = 0; i < 36; i++) {
                    mirror.setTilt(1.0, i * 10 - 180);
                    switch ((i / 9) % 4) {
                        case 0:
                            mirror.setSingleZernikeFactor(Z_ComX_Flag, mirror.getMinZernikeAmplitude());
                            break;
                        case 1:
                            mirror.setSingleZernikeFactor(Z_ComY_Flag, mirror.getMinZernikeAmplitude());
                            break;
                        case 2:
                            mirror.setSingleZernikeFactor(Z_ComX_Flag, mirror.getMaxZernikeAmplitude());
                            break;
                        default:
                            mirror.setSingleZernikeFactor(Z_ComY_Flag, mirror.getMaxZernikeAmplitude());
                            break;
                    }
                    Thread.sleep(100);
                }
            }
            mirror.close();
        } else {
            System.out.println("Could not connect to mirror " + mirror.getSerialNumber());
        }
    }
}

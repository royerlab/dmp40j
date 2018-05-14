package dmp40j;

import dmp40j.bindings.TLDFM_64Library;
import org.bridj.CLong;
import org.bridj.Pointer;

/**
 * DMP40J
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 05 2018
 */
public class DMP40J {

    Object mLock = new Object();

    long mInstrumentHandle;

    public DMP40J(String pDeviceSerialName) {

        final Pointer<Byte> lPointerToSerialNumber = Pointer.pointerToCString(pDeviceSerialName);

        Pointer<CLong> lInstrumentHandle = Pointer.allocateCLong();

        TLDFM_64Library.TLDFM_init(lPointerToSerialNumber, (short)0, (short)0, lInstrumentHandle);

        mInstrumentHandle = lInstrumentHandle.getLong();


        final Pointer<Byte> manufacturerName = Pointer.allocateBytes(256);
        TLDFM_64Library.TLDFM_get_manufacturer_name(mInstrumentHandle, manufacturerName);


        TLDFM_64Library.TLDFM_close(lInstrumentHandle.getLong());
        lInstrumentHandle.release();
        lPointerToSerialNumber.release();
    }


    public String getLastErrorString()
    {
        synchronized (mLock)
        {
            final Pointer<Integer> errorNo = Pointer.allocateInt();
            final Pointer<Byte> errMsg = Pointer.allocateBytes(256);
            TLDFM_64Library.ViStatus viStatus = new TLDFM_64Library.ViStatus() {};

            TLDFM_64Library.TLDFM_error_message(mInstrumentHandle, viStatus, errMsg);

            final String lErrorString = new String(errMsg.getBytes());
            errorNo.release();
            errMsg.release();
            return lErrorString;
        }
    }
}

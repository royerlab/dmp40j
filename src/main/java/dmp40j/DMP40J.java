package dmp40j;


import dmp40j.bindings.TLDFM_64Library;
import org.bridj.CLong;
import org.bridj.Pointer;

import java.util.Arrays;

import static dmp40j.bindings.TLDFM_64Library.*;


/**
 * DMP40J
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 05 2018
 */
public class DMP40J {

    public final static int VI_FIND_BUFLEN = 256;

    public final static int MAX_SEGMENTS = 40;

    public final static int TLDFMX_Z4_AMPL_POS = 0;
    public final static int TLDFMX_Z5_AMPL_POS = 1;
    public final static int TLDFMX_Z6_AMPL_POS = 2;
    public final static int TLDFMX_Z7_AMPL_POS = 3;
    public final static int TLDFMX_Z8_AMPL_POS = 4;
    public final static int TLDFMX_Z9_AMPL_POS = 5;
    public final static int TLDFMX_Z10_AMPL_POS = 6;
    public final static int TLDFMX_Z11_AMPL_POS = 7;
    public final static int TLDFMX_Z12_AMPL_POS = 8;
    public final static int TLDFMX_Z13_AMPL_POS = 9;
    public final static int TLDFMX_Z14_AMPL_POS = 10;
    public final static int TLDFMX_Z15_AMPL_POS = 11;

    public static String translateStatus(long status) {
        switch ((int) status){

            // Source: visa.h:
            //#define VI_SUCCESS_EVENT_EN                   (0x3FFF0002L) /* 3FFF0002,  */
            case  1073676290 : return "VI_SUCCESS_EVENT_EN";
            //#define VI_SUCCESS_EVENT_DIS                  (0x3FFF0003L) /* 3FFF0003,  */
            case  1073676291 : return "VI_SUCCESS_EVENT_DIS";
            //#define VI_SUCCESS_QUEUE_EMPTY                (0x3FFF0004L) /* 3FFF0004,  */
            case  1073676292 : return "VI_SUCCESS_QUEUE_EMPTY";
            //#define VI_SUCCESS_TERM_CHAR                  (0x3FFF0005L) /* 3FFF0005,  */
            case  1073676293 : return "VI_SUCCESS_TERM_CHAR";
            //#define VI_SUCCESS_MAX_CNT                    (0x3FFF0006L) /* 3FFF0006,  */
            case  1073676294 : return "VI_SUCCESS_MAX_CNT";
            //#define VI_SUCCESS_DEV_NPRESENT               (0x3FFF007DL) /* 3FFF007D,  */
            case  1073676413 : return "VI_SUCCESS_DEV_NPRESENT";
            //#define VI_SUCCESS_TRIG_MAPPED                (0x3FFF007EL) /* 3FFF007E,  */
            case  1073676414 : return "VI_SUCCESS_TRIG_MAPPED";
            //#define VI_SUCCESS_QUEUE_NEMPTY               (0x3FFF0080L) /* 3FFF0080,  */
            case  1073676416 : return "VI_SUCCESS_QUEUE_NEMPTY";
            //#define VI_SUCCESS_NCHAIN                     (0x3FFF0098L) /* 3FFF0098,  */
            case  1073676440 : return "VI_SUCCESS_NCHAIN";
            //#define VI_SUCCESS_NESTED_SHARED              (0x3FFF0099L) /* 3FFF0099,  */
            case  1073676441 : return "VI_SUCCESS_NESTED_SHARED";
            //#define VI_SUCCESS_NESTED_EXCLUSIVE           (0x3FFF009AL) /* 3FFF009A,  */
            case  1073676442 : return "VI_SUCCESS_NESTED_EXCLUSIVE";
            //#define VI_SUCCESS_SYNC                       (0x3FFF009BL) /* 3FFF009B,  */
            case  1073676443 : return "VI_SUCCESS_SYNC";
            //#define VI_WARN_QUEUE_OVERFLOW                (0x3FFF000CL) /* 3FFF000C, */
            case  1073676300 : return "VI_WARN_QUEUE_OVERFLOW";
            //#define VI_WARN_CONFIG_NLOADED                (0x3FFF0077L) /* 3FFF0077,  */
            case  1073676407 : return "VI_WARN_CONFIG_NLOADED";
            //#define VI_WARN_NULL_OBJECT                   (0x3FFF0082L) /* 3FFF0082,  */
            case  1073676418 : return "VI_WARN_NULL_OBJECT";
            //#define VI_WARN_NSUP_ATTR_STATE               (0x3FFF0084L) /* 3FFF0084,  */
            case  1073676420 : return "VI_WARN_NSUP_ATTR_STATE";
            //#define VI_WARN_UNKNOWN_STATUS                (0x3FFF0085L) /* 3FFF0085,  */
            case  1073676421 : return "VI_WARN_UNKNOWN_STATUS";
            //#define VI_WARN_NSUP_BUF                      (0x3FFF0088L) /* 3FFF0088,  */
            case  1073676424 : return "VI_WARN_NSUP_BUF";
            //#define VI_WARN_EXT_FUNC_NIMPL                (0x3FFF00A9L) /* 3FFF00A9,  */
            case  1073676457 : return "VI_WARN_EXT_FUNC_NIMPL";
            //#define VI_ERROR_SYSTEM_ERROR       (_VI_ERROR+0x3FFF0000L) /* BFFF0000, */
            case  -1073807360 : return "VI_ERROR_SYSTEM_ERROR";
            //#define VI_ERROR_INV_OBJECT         (_VI_ERROR+0x3FFF000EL) /* BFFF000E, */
            case  -1073807346 : return "VI_ERROR_INV_OBJECT";
            //#define VI_ERROR_RSRC_LOCKED        (_VI_ERROR+0x3FFF000FL) /* BFFF000F, */
            case  -1073807345 : return "VI_ERROR_RSRC_LOCKED";
            //#define VI_ERROR_INV_EXPR           (_VI_ERROR+0x3FFF0010L) /* BFFF0010, */
            case  -1073807344 : return "VI_ERROR_INV_EXPR";
            //#define VI_ERROR_RSRC_NFOUND        (_VI_ERROR+0x3FFF0011L) /* BFFF0011, */
            case  -1073807343 : return "VI_ERROR_RSRC_NFOUND";
            //#define VI_ERROR_INV_RSRC_NAME      (_VI_ERROR+0x3FFF0012L) /* BFFF0012, */
            case  -1073807342 : return "VI_ERROR_INV_RSRC_NAME";
            //#define VI_ERROR_INV_ACC_MODE       (_VI_ERROR+0x3FFF0013L) /* BFFF0013, */
            case  -1073807341 : return "VI_ERROR_INV_ACC_MODE";
            //#define VI_ERROR_TMO                (_VI_ERROR+0x3FFF0015L) /* BFFF0015, */
            case  -1073807339 : return "VI_ERROR_TMO";
            //#define VI_ERROR_CLOSING_FAILED     (_VI_ERROR+0x3FFF0016L) /* BFFF0016, */
            case  -1073807338 : return "VI_ERROR_CLOSING_FAILED";
            //#define VI_ERROR_INV_DEGREE         (_VI_ERROR+0x3FFF001BL) /* BFFF001B, */
            case  -1073807333 : return "VI_ERROR_INV_DEGREE";
            //#define VI_ERROR_INV_JOB_ID         (_VI_ERROR+0x3FFF001CL) /* BFFF001C, */
            case  -1073807332 : return "VI_ERROR_INV_JOB_ID";
            //#define VI_ERROR_NSUP_ATTR          (_VI_ERROR+0x3FFF001DL) /* BFFF001D, */
            case  -1073807331 : return "VI_ERROR_NSUP_ATTR";
            //#define VI_ERROR_NSUP_ATTR_STATE    (_VI_ERROR+0x3FFF001EL) /* BFFF001E, */
            case  -1073807330 : return "VI_ERROR_NSUP_ATTR_STATE";
            //#define VI_ERROR_ATTR_READONLY      (_VI_ERROR+0x3FFF001FL) /* BFFF001F, */
            case  -1073807329 : return "VI_ERROR_ATTR_READONLY";
            //#define VI_ERROR_INV_LOCK_TYPE      (_VI_ERROR+0x3FFF0020L) /* BFFF0020, */
            case  -1073807328 : return "VI_ERROR_INV_LOCK_TYPE";
            //#define VI_ERROR_INV_ACCESS_KEY     (_VI_ERROR+0x3FFF0021L) /* BFFF0021, */
            case  -1073807327 : return "VI_ERROR_INV_ACCESS_KEY";
            //#define VI_ERROR_INV_EVENT          (_VI_ERROR+0x3FFF0026L) /* BFFF0026, */
            case  -1073807322 : return "VI_ERROR_INV_EVENT";
            //#define VI_ERROR_INV_MECH           (_VI_ERROR+0x3FFF0027L) /* BFFF0027, */
            case  -1073807321 : return "VI_ERROR_INV_MECH";
            //#define VI_ERROR_HNDLR_NINSTALLED   (_VI_ERROR+0x3FFF0028L) /* BFFF0028, */
            case  -1073807320 : return "VI_ERROR_HNDLR_NINSTALLED";
            //#define VI_ERROR_INV_HNDLR_REF      (_VI_ERROR+0x3FFF0029L) /* BFFF0029, */
            case  -1073807319 : return "VI_ERROR_INV_HNDLR_REF";
            //#define VI_ERROR_INV_CONTEXT        (_VI_ERROR+0x3FFF002AL) /* BFFF002A, */
            case  -1073807318 : return "VI_ERROR_INV_CONTEXT";
            //#define VI_ERROR_QUEUE_OVERFLOW     (_VI_ERROR+0x3FFF002DL) /* BFFF002D, */
            case  -1073807315 : return "VI_ERROR_QUEUE_OVERFLOW";
            //#define VI_ERROR_NENABLED           (_VI_ERROR+0x3FFF002FL) /* BFFF002F, */
            case  -1073807313 : return "VI_ERROR_NENABLED";
            //#define VI_ERROR_ABORT              (_VI_ERROR+0x3FFF0030L) /* BFFF0030, */
            case  -1073807312 : return "VI_ERROR_ABORT";
            //#define VI_ERROR_RAW_WR_PROT_VIOL   (_VI_ERROR+0x3FFF0034L) /* BFFF0034, */
            case  -1073807308 : return "VI_ERROR_RAW_WR_PROT_VIOL";
            //#define VI_ERROR_RAW_RD_PROT_VIOL   (_VI_ERROR+0x3FFF0035L) /* BFFF0035, */
            case  -1073807307 : return "VI_ERROR_RAW_RD_PROT_VIOL";
            //#define VI_ERROR_OUTP_PROT_VIOL     (_VI_ERROR+0x3FFF0036L) /* BFFF0036, */
            case  -1073807306 : return "VI_ERROR_OUTP_PROT_VIOL";
            //#define VI_ERROR_INP_PROT_VIOL      (_VI_ERROR+0x3FFF0037L) /* BFFF0037, */
            case  -1073807305 : return "VI_ERROR_INP_PROT_VIOL";
            //#define VI_ERROR_BERR               (_VI_ERROR+0x3FFF0038L) /* BFFF0038, */
            case  -1073807304 : return "VI_ERROR_BERR";
            //#define VI_ERROR_IN_PROGRESS        (_VI_ERROR+0x3FFF0039L) /* BFFF0039, */
            case  -1073807303 : return "VI_ERROR_IN_PROGRESS";
            //#define VI_ERROR_INV_SETUP          (_VI_ERROR+0x3FFF003AL) /* BFFF003A, */
            case  -1073807302 : return "VI_ERROR_INV_SETUP";
            //#define VI_ERROR_QUEUE_ERROR        (_VI_ERROR+0x3FFF003BL) /* BFFF003B, */
            case  -1073807301 : return "VI_ERROR_QUEUE_ERROR";
            //#define VI_ERROR_ALLOC              (_VI_ERROR+0x3FFF003CL) /* BFFF003C, */
            case  -1073807300 : return "VI_ERROR_ALLOC";
            //#define VI_ERROR_INV_MASK           (_VI_ERROR+0x3FFF003DL) /* BFFF003D, */
            case  -1073807299 : return "VI_ERROR_INV_MASK";
            //#define VI_ERROR_IO                 (_VI_ERROR+0x3FFF003EL) /* BFFF003E, */
            case  -1073807298 : return "VI_ERROR_IO";
            //#define VI_ERROR_INV_FMT            (_VI_ERROR+0x3FFF003FL) /* BFFF003F, */
            case  -1073807297 : return "VI_ERROR_INV_FMT";
            //#define VI_ERROR_NSUP_FMT           (_VI_ERROR+0x3FFF0041L) /* BFFF0041, */
            case  -1073807295 : return "VI_ERROR_NSUP_FMT";
            //#define VI_ERROR_LINE_IN_USE        (_VI_ERROR+0x3FFF0042L) /* BFFF0042, */
            case  -1073807294 : return "VI_ERROR_LINE_IN_USE";
            //#define VI_ERROR_NSUP_MODE          (_VI_ERROR+0x3FFF0046L) /* BFFF0046, */
            case  -1073807290 : return "VI_ERROR_NSUP_MODE";
            //#define VI_ERROR_SRQ_NOCCURRED      (_VI_ERROR+0x3FFF004AL) /* BFFF004A, */
            case  -1073807286 : return "VI_ERROR_SRQ_NOCCURRED";
            //#define VI_ERROR_INV_SPACE          (_VI_ERROR+0x3FFF004EL) /* BFFF004E, */
            case  -1073807282 : return "VI_ERROR_INV_SPACE";
            //#define VI_ERROR_INV_OFFSET         (_VI_ERROR+0x3FFF0051L) /* BFFF0051, */
            case  -1073807279 : return "VI_ERROR_INV_OFFSET";
            //#define VI_ERROR_INV_WIDTH          (_VI_ERROR+0x3FFF0052L) /* BFFF0052, */
            case  -1073807278 : return "VI_ERROR_INV_WIDTH";
            //#define VI_ERROR_NSUP_OFFSET        (_VI_ERROR+0x3FFF0054L) /* BFFF0054, */
            case  -1073807276 : return "VI_ERROR_NSUP_OFFSET";
            //#define VI_ERROR_NSUP_VAR_WIDTH     (_VI_ERROR+0x3FFF0055L) /* BFFF0055, */
            case  -1073807275 : return "VI_ERROR_NSUP_VAR_WIDTH";
            //#define VI_ERROR_WINDOW_NMAPPED     (_VI_ERROR+0x3FFF0057L) /* BFFF0057, */
            case  -1073807273 : return "VI_ERROR_WINDOW_NMAPPED";
            //#define VI_ERROR_RESP_PENDING       (_VI_ERROR+0x3FFF0059L) /* BFFF0059, */
            case  -1073807271 : return "VI_ERROR_RESP_PENDING";
            //#define VI_ERROR_NLISTENERS         (_VI_ERROR+0x3FFF005FL) /* BFFF005F, */
            case  -1073807265 : return "VI_ERROR_NLISTENERS";
            //#define VI_ERROR_NCIC               (_VI_ERROR+0x3FFF0060L) /* BFFF0060, */
            case  -1073807264 : return "VI_ERROR_NCIC";
            //#define VI_ERROR_NSYS_CNTLR         (_VI_ERROR+0x3FFF0061L) /* BFFF0061, */
            case  -1073807263 : return "VI_ERROR_NSYS_CNTLR";
            //#define VI_ERROR_NSUP_OPER          (_VI_ERROR+0x3FFF0067L) /* BFFF0067, */
            case  -1073807257 : return "VI_ERROR_NSUP_OPER";
            //#define VI_ERROR_INTR_PENDING       (_VI_ERROR+0x3FFF0068L) /* BFFF0068, */
            case  -1073807256 : return "VI_ERROR_INTR_PENDING";
            //#define VI_ERROR_ASRL_PARITY        (_VI_ERROR+0x3FFF006AL) /* BFFF006A, */
            case  -1073807254 : return "VI_ERROR_ASRL_PARITY";
            //#define VI_ERROR_ASRL_FRAMING       (_VI_ERROR+0x3FFF006BL) /* BFFF006B, */
            case  -1073807253 : return "VI_ERROR_ASRL_FRAMING";
            //#define VI_ERROR_ASRL_OVERRUN       (_VI_ERROR+0x3FFF006CL) /* BFFF006C, */
            case  -1073807252 : return "VI_ERROR_ASRL_OVERRUN";
            //#define VI_ERROR_TRIG_NMAPPED       (_VI_ERROR+0x3FFF006EL) /* BFFF006E, */
            case  -1073807250 : return "VI_ERROR_TRIG_NMAPPED";
            //#define VI_ERROR_NSUP_ALIGN_OFFSET  (_VI_ERROR+0x3FFF0070L) /* BFFF0070, */
            case  -1073807248 : return "VI_ERROR_NSUP_ALIGN_OFFSET";
            //#define VI_ERROR_USER_BUF           (_VI_ERROR+0x3FFF0071L) /* BFFF0071, */
            case  -1073807247 : return "VI_ERROR_USER_BUF";
            //#define VI_ERROR_RSRC_BUSY          (_VI_ERROR+0x3FFF0072L) /* BFFF0072, */
            case  -1073807246 : return "VI_ERROR_RSRC_BUSY";
            //#define VI_ERROR_NSUP_WIDTH         (_VI_ERROR+0x3FFF0076L) /* BFFF0076, */
            case  -1073807242 : return "VI_ERROR_NSUP_WIDTH";
            //#define VI_ERROR_INV_PARAMETER      (_VI_ERROR+0x3FFF0078L) /* BFFF0078, */
            case  -1073807240 : return "VI_ERROR_INV_PARAMETER";
            //#define VI_ERROR_INV_PROT           (_VI_ERROR+0x3FFF0079L) /* BFFF0079, */
            case  -1073807239 : return "VI_ERROR_INV_PROT";
            //#define VI_ERROR_INV_SIZE           (_VI_ERROR+0x3FFF007BL) /* BFFF007B, */
            case  -1073807237 : return "VI_ERROR_INV_SIZE";
            //#define VI_ERROR_WINDOW_MAPPED      (_VI_ERROR+0x3FFF0080L) /* BFFF0080, */
            case  -1073807232 : return "VI_ERROR_WINDOW_MAPPED";
            //#define VI_ERROR_NIMPL_OPER         (_VI_ERROR+0x3FFF0081L) /* BFFF0081, */
            case  -1073807231 : return "VI_ERROR_NIMPL_OPER";
            //#define VI_ERROR_INV_LENGTH         (_VI_ERROR+0x3FFF0083L) /* BFFF0083, */
            case  -1073807229 : return "VI_ERROR_INV_LENGTH";
            //#define VI_ERROR_INV_MODE           (_VI_ERROR+0x3FFF0091L) /* BFFF0091, */
            case  -1073807215 : return "VI_ERROR_INV_MODE";
            //#define VI_ERROR_SESN_NLOCKED       (_VI_ERROR+0x3FFF009CL) /* BFFF009C, */
            case  -1073807204 : return "VI_ERROR_SESN_NLOCKED";
            //#define VI_ERROR_MEM_NSHARED        (_VI_ERROR+0x3FFF009DL) /* BFFF009D, */
            case  -1073807203 : return "VI_ERROR_MEM_NSHARED";
            //#define VI_ERROR_LIBRARY_NFOUND     (_VI_ERROR+0x3FFF009EL) /* BFFF009E, */
            case  -1073807202 : return "VI_ERROR_LIBRARY_NFOUND";
            //#define VI_ERROR_NSUP_INTR          (_VI_ERROR+0x3FFF009FL) /* BFFF009F, */
            case  -1073807201 : return "VI_ERROR_NSUP_INTR";
            //#define VI_ERROR_INV_LINE           (_VI_ERROR+0x3FFF00A0L) /* BFFF00A0, */
            case  -1073807200 : return "VI_ERROR_INV_LINE";
            //#define VI_ERROR_FILE_ACCESS        (_VI_ERROR+0x3FFF00A1L) /* BFFF00A1, */
            case  -1073807199 : return "VI_ERROR_FILE_ACCESS";
            //#define VI_ERROR_FILE_IO            (_VI_ERROR+0x3FFF00A2L) /* BFFF00A2, */
            case  -1073807198 : return "VI_ERROR_FILE_IO";
            //#define VI_ERROR_NSUP_LINE          (_VI_ERROR+0x3FFF00A3L) /* BFFF00A3, */
            case  -1073807197 : return "VI_ERROR_NSUP_LINE";
            //#define VI_ERROR_NSUP_MECH          (_VI_ERROR+0x3FFF00A4L) /* BFFF00A4, */
            case  -1073807196 : return "VI_ERROR_NSUP_MECH";
            //#define VI_ERROR_INTF_NUM_NCONFIG   (_VI_ERROR+0x3FFF00A5L) /* BFFF00A5, */
            case  -1073807195 : return "VI_ERROR_INTF_NUM_NCONFIG";
            //#define VI_ERROR_CONN_LOST          (_VI_ERROR+0x3FFF00A6L) /* BFFF00A6, */
            case  -1073807194 : return "VI_ERROR_CONN_LOST";
            //#define VI_ERROR_MACHINE_NAVAIL     (_VI_ERROR+0x3FFF00A7L) /* BFFF00A7, */
            case  -1073807193 : return "VI_ERROR_MACHINE_NAVAIL";
            //#define VI_ERROR_NPERMISSION        (_VI_ERROR+0x3FFF00A8L) /* BFFF00A8, */
            case  -1073807192: return "VI_ERROR_NPERMISSION";
            //


            case VI_SUCCESS              : return "Initialization successful";
            case VI_WARN_NSUP_ID_QUERY   : return "Identification query not supported";
            case VI_WARN_NSUP_RESET      : return "Reset not supported";

            case VI_ERROR_FAIL_ID_QUERY  : return "Instrument identification query failed";
            //case VI_ERROR_SYSTEM_ERROR   : return "Unspecified driver error";
            //case VI_ERROR_ALLOC          : return "Error during memory allocation";
            case VI_ERROR_INV_RESPONSE   : return "Invalid device response";
            case TLDFM_ERROR_CHECKSUM    : return "Answer checksum is wrong";

            default: return "Unknown status (" + status + ")";
        }
    }
}

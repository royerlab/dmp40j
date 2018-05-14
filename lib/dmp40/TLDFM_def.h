//==============================================================================
//
// Title:       TLDFM_def.h
// Purpose:     A short description of the interface.
//
// Created on:  8/2/2012 at 3:05:18 PM by cwestphal.
// 
// Copyright:   Thorlabs GmbH. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDFM_DEF_H__
#define __TLDFM_DEF_H__                                                  

#ifdef __cplusplus
extern "C" {
#endif

//==============================================================================
// Include files
#include <vpptype.h>

//==============================================================================
// Constants
	
#define TLDFM_VI_FIND_RSC_PATTERN       "USB?*?{VI_ATTR_MANF_ID==0x1313 && VI_ATTR_MODEL_CODE==0x8016}"

#define TLDFM_MAX_STRING_LENGTH         60   // general string (char[]) size

#define TLDFM_MAX_INSTR_NAME_LENGTH     28
#define TLDFM_MAX_SN_LENGTH             28
                                          
#define TLDFM_BUFFER_SIZE               256  // general buffer size
//#ifndef VI_FIND_BUFLEN
//    #define VI_FIND_BUFLEN				256
//#endif
#define TLDFM_USERTEXT_BUFFER_SIZE      61
#define TLDFM_ERR_DESCR_BUFER_SIZE      512

// Driver Error Codes
#define TLDFM_INSTR_WARNING_OFFSET      0x3FFC0900L
#define TLDFM_INSTR_ERROR_OFFSET        (_VI_ERROR + TLDFM_INSTR_WARNING_OFFSET)
#define TLDFM_ERROR_USBCOMM_OFFSET      (_VI_ERROR + 0x3FFC0B00L)
	
#define TLDFM_HEAD_ERR                  (TLDFM_INSTR_ERROR_OFFSET + 0x01)  // unspecified error
#define TLDFM_HEAD_ERR_CMD              (TLDFM_INSTR_ERROR_OFFSET + 0x02)  // unknown command
#define TLDFM_HEAD_ERR_CMD_DIR          (TLDFM_INSTR_ERROR_OFFSET + 0x03)  // not allowed command direction
#define TLDFM_HEAD_ERR_BYTE_CNT         (TLDFM_INSTR_ERROR_OFFSET + 0x04)  // unexpected byte count
#define TLDFM_HEAD_ERR_DATA_RANGE       (TLDFM_INSTR_ERROR_OFFSET + 0x05)  // data out of range
#define TLDFM_HEAD_ERR_NO_MULT_SAMPLE   (TLDFM_INSTR_ERROR_OFFSET + 0x06)  // data is not a multiple of sample count (16)
#define TLDFM_HEAD_ERR_ALLREADY_RUNNING (TLDFM_INSTR_ERROR_OFFSET + 0x07)  // unable to start new transmission cause a brevious transmission is allready running
#define TLDFM_HEAD_ERR_NO_ACCESS        (TLDFM_INSTR_ERROR_OFFSET + 0x08)  // access level required
		
#define TLDFM_ERROR_CHECKSUM            (TLDFM_INSTR_ERROR_OFFSET + 0x09)  // checksum error

#define TLDFM_ERR_CONF_UNAVAILABLE      (TLDFM_INSTR_ERROR_OFFSET + 0x0A)
#define TLDFM_ERR_HYST_UNAVAILABLE      (TLDFM_INSTR_ERROR_OFFSET + 0x0B)
		
#define TLDFM_ERROR_EXTERNAL_PWR    	(TLDFM_INSTR_ERROR_OFFSET + 0x0C)  // no external power
#define TLDFM_ERROR_INTERNAL_PWR    	(TLDFM_INSTR_ERROR_OFFSET + 0x0D)  // no internal power
#define TLDFM_ERROR_OHT_EL          	(TLDFM_INSTR_ERROR_OFFSET + 0x0E)  // electric is to hot
#define TLDFM_ERROR_OHT_HV_IC          	(TLDFM_INSTR_ERROR_OFFSET + 0x0F)  // High Voltage IC's are to hot
#define TLDFM_ERROR_OHT_HV_MI          	(TLDFM_INSTR_ERROR_OFFSET + 0x10)  // High Voltage mirror is to hot

#define TLDFM_ERROR_UNKNOWN_TARGET		(TLDFM_INSTR_ERROR_OFFSET + 0x21)
#define TLDFM_ERROR_NOSUP_TARGET		(TLDFM_INSTR_ERROR_OFFSET + 0x22)  // Target is not supported

#define VI_ERROR_PARAMETER9             (TLDFM_INSTR_ERROR_OFFSET + 0x99)

#define TLDFM_NO_ERROR               	(TLDFM_INSTR_ERROR_OFFSET + 0xFE)
#define TLDFM_ERROR_UNKNOWN          	(TLDFM_INSTR_ERROR_OFFSET + 0xFF)
  
#define TLDFM_WARNING_TEMP_ELECTRIC     (TLDFM_INSTR_WARNING_OFFSET + 0x00)  // electric getting to hot
#define TLDFM_WARNING_TEMP_IC           (TLDFM_INSTR_WARNING_OFFSET + 0x01)  // High Voltage IC's getting to hot
#define TLDFM_WARNING_TEMP_MIRROR       (TLDFM_INSTR_WARNING_OFFSET + 0x03)  // High Voltage mirror getting to hot

#define TLDFM_SELFTEST_SUCCESS          0x0000
#define TLDFM_SELFTEST_NOT_POS          0xFFFF
#define TLDFM_SELFTEST_PWR_ERR          0x0001
#define TLDFM_SELFTEST_TEMP_ERR         0x0010

#define T_MIRROR						(0)
#define T_TILT							(1)
#define T_BOTH							(2)



//==============================================================================
// Types
typedef enum
{
   SM_Auto = 0,
   SM_Manual,
   
} TLDFM_Switch_Mode;

typedef enum
{
   AM_No_Lock = 0,
   AM_Exclusive_Lock = 1,
   AM_Shared_Lock =2,
} TLDFM_Access_Mode;

//==============================================================================
// Callback Function Prototypes
typedef int (_VI_FUNC _VI_PTR StatusUpdate)(ViChar[]);  // Status Callback

#ifdef __cplusplus
}
#endif

#endif  /* ifndef __TLDFM_DEF_H__ */

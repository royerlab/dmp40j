//==============================================================================
//
// Title:		TLDFMX_def.h
//
// Purpose:		Definitions Header.
//
// Created on:	27.05.2015 at 10:52:39 by cwestphal.
//
// Copyright:   Thorlabs GmbH. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDFMX_DEF_H__
#define __TLDFMX_DEF_H__

#ifdef __cplusplus
    extern "C" {
#endif

//==============================================================================
// Include files
#include "TLDFM.h"

//==============================================================================
// Constants
#define TLDFMX_ERR_DESCR_BUFER_SIZE	 (512)

#define TLDFMX_WARNING				 (0x3FFC0100L)
#define TLDFMX_ERROR				 (_VI_ERROR)
#define TLDFMX_ERROR_OFFSET			 (TLDFM_ERROR_UNKNOWN)

#define TLDFMX_ERROR_NODATA			 (TLDFMX_ERROR_OFFSET + 0x01)
#define TLDFMX_ERROR_NOTINIT		 (TLDFMX_ERROR_OFFSET + 0x02)
#define TLDFMX_ERROR_NOSUP_TARGET	 (TLDFMX_ERROR_OFFSET + 0x03)
#define TLDFMX_ERROR_ITERATION		 (TLDFMX_ERROR_OFFSET + 0x04)
#define TLDFMX_ERROR_ITER_AMPL       (TLDFMX_ERROR_OFFSET + 0x05)
#define TLDFMX_ERROR_ITER_CROSS_AMPL (TLDFMX_ERROR_OFFSET + 0x06)
#define TLDFMX_ERROR_ITER_DATA_INV   (TLDFMX_ERROR_OFFSET + 0x07)
#define TLDFMX_ERROR_AMPL_CONVERT    (TLDFMX_ERROR_OFFSET + 0x08)
#define TLDFMX_ERROR_AMPL_RANGE      (TLDFMX_ERROR_OFFSET + 0x09)

#define TLDFMX_ERROR_UNKNOWN		 (TLDFMX_ERROR_OFFSET + 0xFF)
		
#define TLDFMX_MAX_ZERNIKE_TERMS     (12)
#define TLDFMX_Z4_AMPL_POS			 ((ViInt32)0)
#define TLDFMX_Z5_AMPL_POS			 ((ViInt32)1)
#define TLDFMX_Z6_AMPL_POS			 ((ViInt32)2)
#define TLDFMX_Z7_AMPL_POS			 ((ViInt32)3)
#define TLDFMX_Z8_AMPL_POS			 ((ViInt32)4)
#define TLDFMX_Z9_AMPL_POS			 ((ViInt32)5)
#define TLDFMX_Z10_AMPL_POS			 ((ViInt32)6)
#define TLDFMX_Z11_AMPL_POS			 ((ViInt32)7)
#define TLDFMX_Z12_AMPL_POS			 ((ViInt32)8)
#define TLDFMX_Z13_AMPL_POS			 ((ViInt32)9)
#define TLDFMX_Z14_AMPL_POS			 ((ViInt32)10)
#define TLDFMX_Z15_AMPL_POS			 ((ViInt32)11)

//==============================================================================
// Types
typedef ViUInt32 TLDFMX_zernikes_t;
typedef ViUInt32 TLDFMX_rotation_t;
typedef ViUInt32 TLDFMX_flip_t;

typedef enum
{
	ST_No_Data_Flag           = TLDFMX_ERROR_NODATA,
	ST_Initialized_Flag       = 0x0001,
	ST_IsFactoryPattern_Flag  = 0x0002,
	ST_IsFactorySettings_Flag = 0x0003,	// = ST_Initialized_Flag | ST_IsFactoryPattern_Flag
	ST_Flipped_Flag           = 0x0004,
	ST_Rotated_Flag           = 0x0008,
	ST_Offset_Applied_Flag    = 0x0010,
	ST_System_Measuret_Flag   = 0x0020,
	
} TLDFMX_self_test_flag_t;

typedef enum
{
	R_None_Flag   = 0x00000000,
	
	R_45Deg_Flag  = 0x00000001,
	R_90Deg_Flag  = 0x00000002,
	R_135Deg_Flag = 0x00000003,
	
} TLDFMX_rotation_flag_t;


typedef enum
{
	F_None_Flag       = 0x00000000,
	
	F_Horizontal_Flag = 0x00000001,
	F_Vertical_Flag   = 0x00000002,
	F_Hor_Ver_Flag    = 0x00000003,
	
} TLDFMX_flip_flag_t;

typedef enum
{
	Z_None_Flag  = 0x00000000,
	
	Z_Ast45_Flag = 0x00000001,
	Z_Def_Flag   = 0x00000002,
	Z_Ast0_Flag  = 0x00000004,
	Z_TreY_Flag  = 0x00000008,
	Z_ComX_Flag  = 0x00000010,
	Z_ComY_Flag  = 0x00000020,
	Z_TreX_Flag  = 0x00000040,
	Z_TetY_Flag  = 0x00000080,
	Z_SAstY_Flag = 0x00000100,
	Z_SAb3_Flag  = 0x00000200,
	Z_SAstX_Flag = 0x00000400,
	Z_TetX_Flag  = 0x00000800,
	
	Z_All_Flag   = 0xFFFFFFFF
	
} TLDFMX_zernike_flag_t;


//==============================================================================
// External variables

//==============================================================================
// Global functions


#ifdef __cplusplus
    }
#endif

#endif  // __TLDFMX_DEF_H__

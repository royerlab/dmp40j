//==============================================================================
//
// Title:		TLDSTP_def.h
// Purpose:		A short description of the interface.
//
// Created on:	21.07.2015 at 11:13:07 by temp.
// Copyright:	. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDSTP_def_H__
#define __TLDSTP_def_H__

#ifdef __cplusplus
    extern "C" {
#endif

//==============================================================================
// Include files
#include "vpptype.h"

//==============================================================================
// Constants
#define TLDSTP_ERR_DESCR_BUFER_SIZE	(256)

//==============================================================================
// Types
	typedef int (_VI_FUNC _VI_PTR StatusCallback) (ViChar[]);
	typedef int (_VI_FUNC _VI_PTR ZernikeCallback) (ViReal32[]);
	
	typedef enum
	{
		CT_Connecting,
		CT_Active,
		CT_Error,
		CT_Idle,
		CT_Unconnected
		
	} TLDSTP_connection_status_t;

//==============================================================================
// External variables

//==============================================================================
// Global functions

#ifdef __cplusplus
    }
#endif

#endif  /* ndef __TLDSTP_def_H__ */

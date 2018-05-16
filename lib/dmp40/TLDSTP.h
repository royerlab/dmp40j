//==============================================================================
//
// Title:		TLDSTP.h
// Purpose:		A short description of the interface.
//
// Created on:	20.07.2015 at 17:47:24 by cwestphal.
// Copyright:	Thorlabs GmbH. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDSTP_H__
#define __TLDSTP_H__

#if _NI_mswin64_
#error The LabWindows/CVI DataSocket library is not supported on x64.
#endif

#ifdef __cplusplus
    extern "C" {
#endif

//==============================================================================
// Include files

#include "TLDSTP_def.h"

//==============================================================================
// Constants

//==============================================================================
// Types

//==============================================================================
// External variables

//==============================================================================
// Global functions
		
	/**************************************************************************
	 * Initialize and Close Functions
	 **************************************************************************/
	ViStatus _VI_FUNC TLDSTP_init (ViChar     dstpUrl[],
								   ViPSession pSessionHdl);
	
	ViStatus _VI_FUNC TLDSTP_close (ViSession sessionHdl);
	
	/**************************************************************************
	 * Utility Functions
	 **************************************************************************/
	ViStatus _VI_FUNC TLDSTP_reset (ViSession sessionHdl);
	
	ViStatus _VI_FUNC TLDSTP_self_test (ViSession sessionHdl,
										ViPInt16  pSelfTestResultCode,
										ViChar    selfTestMessage[]);
	
	ViStatus _VI_FUNC TLDSTP_error_query (ViSession sessionHdl,
										  ViPStatus errorCode,
										  ViChar    errorMessage[]);
	
	ViStatus _VI_FUNC TLDSTP_error_message (ViSession sessionHdl,
											ViStatus  errorCode,
											ViChar    errorMessage[]);
	
	ViStatus _VI_FUNC TLDSTP_revision_query (ViSession sessionHdl,
											 ViChar    driverRevision[],
											 ViChar    firmwareRevision[]);
	
	/**************************************************************************
	 * Action/Status Functions
	 **************************************************************************/
	
		
	/**************************************************************************
	 * Service Functions
	 **************************************************************************/
		
		
	/**************************************************************************
	 * Configuration Functions
	 **************************************************************************/
	ViStatus _VI_FUNC TLDSTP_add_status_callback (ViSession      sessionHdl,
												  StatusCallback functionPtr);
	
	ViStatus _VI_FUNC TLDSTP_remove_status_callback (ViSession      sessionHdl,
													 StatusCallback functionPtr);
	
	ViStatus _VI_FUNC TLDSTP_add_zernike_callback (ViSession       sessionHdl,
												   ZernikeCallback functionPtr);
	
	ViStatus _VI_FUNC TLDSTP_remove_zernike_callback (ViSession       sessionHdl,
													  ZernikeCallback functionPtr);
	
	/**************************************************************************
	 * Data Functions
	 **************************************************************************/



#ifdef __cplusplus
    }
#endif

#endif  // #ifndef __TLDSTP_H__

//==============================================================================
//
// Title:       TLDFM.h
//
// Purpose:     TLDFM Extension library.
//
// Created on:  27.05.2015 at 09:56:39 by cwestphal.
// 
// Copyright:   Thorlabs GmbH. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDFMX_H__
#define __TLDFMX_H__

#ifdef __cplusplus
    extern "C" {
#endif

//==============================================================================
// Include files
#include "TLDFMX_def.h"

//==============================================================================
// Global functions

ViStatus _VI_FUNC TLDFMX_init (ViRsrc     resourceName,
                               ViBoolean  IDQuery,
                               ViBoolean  resetDevice,
                               ViSession* pInstrumentHandle);

ViStatus _VI_FUNC TLDFMX_close (ViSession instrumentHandle);

//==============================================================================
// Utility Functions

ViStatus _VI_FUNC TLDFMX_reset (ViSession instrumentHandle);

ViStatus _VI_FUNC TLDFMX_self_test (ViSession instrumentHandle,
                                    ViInt16*  pSelfTestResult,
                                    ViChar    selfTestMessage[]);

ViStatus _VI_FUNC TLDFMX_error_query (ViSession instrumentHandle,
                                      ViInt32*  pErrorCode,
									  ViChar    errorMessage[]);

ViStatus _VI_FUNC TLDFMX_error_message (ViSession instrumentHandle,
                                        ViStatus  errorCode,
                                        ViChar    errorMessage[]);

ViStatus _VI_FUNC TLDFMX_revision_query (ViSession instrumentHandle,
                                         ViChar    instrumentDriverRevision[],
                                         ViChar    firmwareRevision[]);

//==============================================================================
// Action/Status Functions

ViStatus _VI_FUNC TLDFMX_relax (ViSession instrumentHandle,
								ViUInt32  devicePart,
								ViBoolean isFirstStep,
								ViBoolean reload,
								ViReal64  relaxPatternMirror[],
								ViReal64  relaxPatternArms[],
								ViInt32*  pRemainingSteps);

ViStatus _VI_FUNC TLDFMX_measure_system_parameters (ViSession instrumentHandle,
													ViBoolean isFirstStep,
													ViReal32  measuredZernikeAmplitudes[],
													ViReal64  nextMirrorPattern[],
													ViInt32*  pRemainingSteps);

ViStatus _VI_FUNC TLDFMX_get_flat_wavefront (ViSession         instrumentHandle,
											 TLDFMX_zernikes_t flatZernikes,
											 ViReal32          measuredZernikeAmplitudes[],
											 ViReal64          deviceZernikeAmplitudes[],
											 ViReal64          voltagePattern[]);

//==============================================================================
// Configuration Functions

ViStatus _VI_FUNC TLDFMX_set_single_voltage_setpoint (ViSession    instrumentHandle,
													  ViInt32      segmentID,
													  ViReal64     voltage);

ViStatus _VI_FUNC TLDFMX_set_voltages_setpoint (ViSession    instrumentHandle,
												ViReal64     mirrorVoltages[]);

ViStatus _VI_FUNC TLDFMX_calculate_single_zernike_pattern (ViSession             instrumentHandle,
														   TLDFMX_zernike_flag_t zernike,
														   ViReal64              deviceZernikeAmplitude,
														   ViReal64              mirrorPattern[]);

ViStatus _VI_FUNC TLDFMX_calculate_zernike_pattern (ViSession         instrumentHandle,
													TLDFMX_zernikes_t zernikes,
													ViReal64          deviceZernikeAmplitudes[],
													ViReal64          mirrorPattern[]);

ViStatus _VI_FUNC TLDFMX_set_flip_mode (ViSession     instrumentHandle,
										TLDFMX_flip_t flipMode);

ViStatus _VI_FUNC TLDFMX_get_flip_mode (ViSession      instrumentHandle,
										TLDFMX_flip_t* pFlipMode);

ViStatus _VI_FUNC TLDFMX_set_rotation_mode (ViSession         instrumentHandle,
											TLDFMX_rotation_t rotation);

ViStatus _VI_FUNC TLDFMX_get_rotation_mode (ViSession          instrumentHandle,
											TLDFMX_rotation_t* pRotationMode);

ViStatus _VI_FUNC TLDFMX_set_pattern_range_check (ViSession instrumentHandle,
												  ViBoolean enable);

ViStatus _VI_FUNC TLDFMX_get_pattern_range_check (ViSession  instrumentHandle,
												  ViBoolean* pEnabled);

//==============================================================================
// Data Functions

ViStatus _VI_FUNC TLDFMX_get_parameters (ViSession instrumentHandle,
										 ViReal64* pMinimumZernikeAmplitude,
										 ViReal64* pMaximumZernikeAmplitude,
										 ViInt32*  pZernikeCount,
										 ViInt32*  pMeasurementSteps,
										 ViInt32*  pRelaxSteps);

ViStatus _VI_FUNC TLDFMX_get_system_parameters (ViSession          instrumentHandle,
												TLDFMX_rotation_t* pRotationMode,
												TLDFMX_flip_t*     pFlipMode,
												ViReal64           maxDeviceZernikeAmplitudes[],
												ViBoolean*         isDataValid);

//==============================================================================
// Helper Functions

ViStatus _VI_FUNC TLDFMX_convert_measured_zernike_amplitudes (ViSession instrumentHandle,
															  ViReal32  measuredZernikeAmplitudes[],
															  ViReal64  deviceZernikeAmplitudes[]);

#ifdef __cplusplus
    }
#endif

#endif  // __TLDFMX_H__

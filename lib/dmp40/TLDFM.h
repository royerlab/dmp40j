//==============================================================================
//
// Title:       TLDFM.h
// Purpose:     A short description of the interface.
//
// Created on:  25.07.2012 at 12:28:39 by cwestphal.                                    
// 
// Copyright:   Thorlabs GmbH. All Rights Reserved.
//
//==============================================================================

#ifndef __TLDFM_H__
#define __TLDFM_H__

#ifdef __cplusplus
    extern "C" {
#endif

//==============================================================================
// Include files
#ifndef NIVISA_USB
#define  NIVISA_USB
#endif
      
#include "TLDFM_def.h"

//==============================================================================
// Global functions
ViStatus _VI_FUNC TLDFM_get_device_count              (ViSession instrumentHandle,
                                                       ViUInt32* pDeviceCount);

ViStatus _VI_FUNC TLDFM_get_device_information        (ViSession  instrumentHandle,
	                                                   ViUInt32   deviceIndex,
													   ViChar     manufacturer[],
													   ViChar     instrumentName[],
													   ViChar     serialNumber[],
													   ViBoolean* pDeviceAvailable,
													   ViChar     resourceName[]);

//******************************************************************************
// required VXIpnp instrument driver functions
      
ViStatus _VI_FUNC TLDFM_init                          (ViRsrc     resourceName,
                                                       ViBoolean  IDQuery,
                                                       ViBoolean  resetDevice,
                                                       ViSession* pInstrumentHandle);

ViStatus _VI_FUNC TLDFM_close                         (ViSession  instrumentHandle);

ViStatus _VI_FUNC TLDFM_reset                         (ViSession  instrumentHandle);

ViStatus _VI_FUNC TLDFM_self_test                     (ViSession  instrumentHandle,
                                                       ViInt16*   pSelfTestResult,
                                                       ViChar     selfTestMessage[]);

ViStatus _VI_FUNC TLDFM_error_query                   (ViSession  instrumentHandle,
                                                       ViInt32*   pErrorCode,
                                                       ViChar     errorMessage[]);
				  
ViStatus _VI_FUNC TLDFM_error_message                 (ViSession  instrumentHandle,
                                                       ViStatus   errorCode,
                                                       ViChar     errorMessage[]);

ViStatus _VI_FUNC TLDFM_revision_query                (ViSession  instrumentHandle,
                                                       ViChar     driverRevision[],
                                                       ViChar     firmwareRevision[]);

//******************************************************************************

ViStatus _VI_FUNC TLDFM_set_USB_access_mode           (ViSession instrumentHandle,
                                                       ViUInt32  accessMode,
                                                       ViString  requestedKey,
                                                       ViChar    accessKey[]);

//******************************************************************************
// segment voltage functions

ViStatus _VI_FUNC TLDFM_get_segment_voltage           (ViSession instrumentHandle,
                                                       ViUInt32  segmentIndex,
                                                       ViReal64* pSegmentVoltage);

ViStatus _VI_FUNC TLDFM_set_segment_voltage           (ViSession instrumentHandle,
                                                       ViUInt32  segmentIndex,
                                                       ViReal64  segmentVoltage);

ViStatus _VI_FUNC TLDFM_get_segment_voltages          (ViSession instrumentHandle,
                                                       ViReal64  segmentVoltages[]);

ViStatus _VI_FUNC TLDFM_set_segment_voltages          (ViSession instrumentHandle,
                                                       ViReal64  segmentVoltages[]);

//******************************************************************************
// tilt element voltage functions

ViStatus _VI_FUNC TLDFM_get_tilt_voltage              (ViSession instrumentHandle,
                                                       ViUInt32  tiltIndex,
                                                       ViReal64* pTiltVoltage);

ViStatus _VI_FUNC TLDFM_set_tilt_voltage              (ViSession instrumentHandle,
                                                       ViUInt32  tiltIndex,
                                                       ViReal64  tiltVoltage);

ViStatus _VI_FUNC TLDFM_get_tilt_voltages             (ViSession instrumentHandle,
                                                       ViReal64  tiltVoltages[]);

ViStatus _VI_FUNC TLDFM_set_tilt_voltages             (ViSession instrumentHandle,
                                                       ViReal64  tiltVoltages[]);

ViStatus _VI_FUNC TLDFM_set_tilt_amplitude_angle      (ViSession instrumentHandle,
                                                       ViReal64  amplitute,
                                                       ViReal64  angle);

//******************************************************************************
// convenience voltage functions
// sets the voltages for all segments and all tilt elements

ViStatus _VI_FUNC TLDFM_get_voltages                  (ViSession instrumentHandle,
                                                       ViReal64  segmentVoltages[],
                                                       ViReal64  tiltVoltages[]);

ViStatus _VI_FUNC TLDFM_set_voltages                  (ViSession instrumentHandle,
                                                       ViReal64  segmentVoltages[],
                                                       ViReal64  tiltVoltages[]);

//******************************************************************************
// device information functions

ViStatus _VI_FUNC TLDFM_get_manufacturer_name         (ViSession instrumentHandle,
                                                       ViChar    manufacturerName[]);

ViStatus _VI_FUNC TLDFM_get_instrument_name           (ViSession instrumentHandle,
                                                       ViChar    instrName[]);

ViStatus _VI_FUNC TLDFM_set_instrument_name           (ViSession instrumentHandle,
                                                       ViChar    instrName[]);

ViStatus _VI_FUNC TLDFM_get_serial_Number             (ViSession instrumentHandle,
                                                       ViChar    serialNumber[]);

ViStatus _VI_FUNC TLDFM_set_serial_number             (ViSession instrumentHandle,
                                                       ViChar    serialNumber[]);

ViStatus _VI_FUNC TLDFM_get_user_text                 (ViSession instrumentHandle,
                                                       ViChar    userText[]);

ViStatus _VI_FUNC TLDFM_set_user_text                 (ViSession instrumentHandle,
                                                       ViChar    userText[]);

ViStatus _VI_FUNC TLDFM_update_firmware               (ViSession instrumentHandle,
                                                       ViChar    firmwareFile[]);

ViStatus _VI_FUNC TLDFM_enable_event                  (ViSession instrumentHandle);

ViStatus _VI_FUNC TLDFM_disable_event                 (ViSession instrumentHandle);

ViStatus _VI_FUNC TLDFM_add_status_delegate           (ViSession    instrumentHandle,
	                                                   StatusUpdate statusUpdateDelegate);

//******************************************************************************

ViStatus _VI_FUNC TLDFM_get_segment_count             (ViSession instrumentHandle,
                                                       ViUInt32* pSegmentCount);

ViStatus _VI_FUNC TLDFM_get_segment_maximum           (ViSession instrumentHandle,
                                                       ViReal64* pSegmentMaximum);

ViStatus _VI_FUNC TLDFM_get_segment_minimum           (ViSession instrumentHandle,
                                                       ViReal64* pSegmentMinimum);

ViStatus _VI_FUNC TLDFM_get_tilt_count                (ViSession instrumentHandle,
                                                       ViUInt32* pTiltCount);

ViStatus _VI_FUNC TLDFM_get_tilt_maximum              (ViSession instrumentHandle,
                                                       ViReal64* pTiltMaximum);

ViStatus _VI_FUNC TLDFM_get_tilt_minimum              (ViSession instrumentHandle,
                                                       ViReal64* pTiltMinimum);

//******************************************************************************

ViStatus _VI_FUNC TLDFM_set_access_level              (ViSession instrumentHandle,
                                                       ViChar    accessLevel,
                                                       ViChar    accessPassword[]);

ViStatus _VI_FUNC TLDFM_get_device_configuration      (ViSession instrumentHandle,
                                                       ViUInt32* pSegmentCnt,
                                                       ViReal64* pMinSegmentVoltage,
                                                       ViReal64* pMaxSegmentVoltage,
                                                       ViReal64* pSegmentCommonVoltageMax,
                                                       ViUInt32* pTiltElementCnt,
                                                       ViReal64* pMinTiltVoltage,
                                                       ViReal64* pMaxTiltVoltage,
                                                       ViReal64* pTiltCommonVoltageMax);

ViStatus _VI_FUNC TLDFM_set_device_configuration		(ViSession instrumentHandle,
														 ViUInt32  segmentCnt,
														 ViReal64  minSegmentVoltage,
														 ViReal64  maxSegmentVoltage,
														 ViReal64  segmentCommonVoltageMax,
														 ViUInt32  tiltElementCnt,
														 ViReal64  minTiltVoltage,
														 ViReal64  maxTiltVoltage,
														 ViReal64  tiltCommonVoltageMax);

ViStatus _VI_FUNC TLDFM_get_device_calibration			(ViSession instrumentHandle,
														 ViReal64* pSegmentVoltageCompensation,
														 ViReal64* pTiltVoltageCompensation,
														 ViReal64* pSingleSegmentTiltVoltage);

ViStatus _VI_FUNC TLDFM_set_device_calibration			(ViSession instrumentHandle,
														 ViReal64  segmentVoltageCompensation,
														 ViReal64  tiltVoltageCompensation,
														 ViReal64  singleSegmentTiltVoltage);

ViStatus _VI_FUNC TLDFM_get_hysteresis_parameters		(ViSession instrumentHandle,
														 ViUInt32  target,
														 ViUInt32* pCount,
														 ViReal64* pNonlinearFactor,
														 ViReal64  arrayTresholdInverter[],
														 ViReal64  arrayWeightInverter[]);

ViStatus _VI_FUNC TLDFM_set_hysteresis_parameters		(ViSession instrumentHandle,
														 ViUInt32  target,
														 ViUInt32  count,
														 ViReal64  nonlinearFactor,
														 ViReal64  arrayTresholdInverter[],
														 ViReal64  arrayWeightInverter[]);

ViStatus _VI_FUNC TLDFM_enabled_hysteresis_compensation (ViSession  instrumentHandle,
                                                         ViUInt32   target,
                                                         ViBoolean* pEnabled);

ViStatus _VI_FUNC TLDFM_enable_hysteresis_compensation  (ViSession instrumentHandle,
                                                         ViUInt32  target,
                                                         ViBoolean enable);

//******************************************************************************

ViStatus _VI_FUNC TLDFM_get_measured_segment_voltage  (ViSession instrumentHandle,
													   ViUInt32  segmentIndex,
													   ViReal64* pSegmentVoltage);

ViStatus _VI_FUNC TLDFM_get_measured_segment_voltages (ViSession instrumentHandle,
													   ViReal64  segmentVoltages[]);

ViStatus _VI_FUNC TLDFM_get_measured_tilt_voltage	  (ViSession instrumentHandle,
													   ViUInt32  tiltIndex,
													   ViReal64* pTiltVoltage);

ViStatus _VI_FUNC TLDFM_get_measured_tilt_voltages	  (ViSession instrumentHandle,
													   ViReal64  tiltVoltages[]);

ViStatus _VI_FUNC TLDFM_get_measured_voltages 		  (ViSession instrumentHandle,
													   ViReal64  segmentVoltages[],
													   ViReal64  tiltVoltages[]);

ViStatus _VI_FUNC TLDFM_get_feedback_voltage          (ViSession instrumentHandle,
                                                       ViReal64* pFeedbackVoltage);

ViStatus _VI_FUNC TLDFM_get_feedback_current          (ViSession instrumentHandle,					  
                                                       ViReal64* pFeedbackCurrent);

ViStatus _VI_FUNC TLDFM_get_feedback                  (ViSession instrumentHandle,
                                                       ViReal64* pFeedbackVoltage,
                                                       ViReal64* pFeedbackCurrent);

ViStatus _VI_FUNC TLDFM_get_monitor_voltage           (ViSession instrumentHandle,
                                                       ViReal64* pMonitorVoltage);

ViStatus _VI_FUNC TLDFM_get_temperatures              (ViSession instrumentHandle,
                                                       ViReal64* pIC1Temperatur,
                                                       ViReal64* pIC2Temperatur,
                                                       ViReal64* pMirrorTemperatur,
                                                       ViReal64* pElectronicTemperatur);


#ifdef __cplusplus
    }
#endif

#endif  /* ifndef __TLDFM_H__ */

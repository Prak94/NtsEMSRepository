/**
 * 
 */
package org.com.nts.ems.response;

/**
 * @author chetan
 *
 */
public class StatusCode {

	private String SUCCESS_STATUS_TYPE = "SU";
	private String ERROR_STATUS_TYPE = "EX";
	private String WARNING_STATUS_TYPE = "WN";

	private String EMP_SERVICE = "EMP";
	private String HR_SERVICE = "HR";
	
	//SUCCESS
	// for employee operation will take 1 to 200 status code
	private String SUCCESSFULLY_FETCHED_DATA_FROM_EMP_DETAILS = "101";
	private String SUCCESSFULLY_FETCHED_DATA_FROM_EMP_MASTER  = "102";
	// for hr operation will take 201 to 400 status code
	private String SUCCESSFULLY_FETCHED_DATA_FROM_HR_DETAILS  = "103";
	private String SUCCESSFULLY_FETCHED_DATA_FROM_HR_MASTER   = "104";

	//FAILURE
	private String ERROR_WHILE_FETCHING_DATA_FROM_EMP_DETAILS = "201";
	private String ERROR_WHILE_FETCHING_DATA_FROM_EMP_MASTER  = "202";
	private String ERROR_WHILE_FETCHING_DATA_FROM_HR_DETAILS  = "203";
	private String ERROR_WHILE_FETCHING_DATA_FROM_HR_MASTER   = "204";

	//WARNING
	private String WARNING_ACCESSING_EMPLOYEE = "1001";

	
	/***
	 * IN response builder class, using response class data.... it should build
	 * statusCode --> as combination of statusType_service_statusCode
	 * statusType as statusType()
	 */
}

/**
 * 
 */
package org.com.nts.ems.utility;

/**
 * <p>
 * class contains URL which mapped on restcontroller
 * 
 * @author chetan
 *
 */
public class EmsRestUrl {

	public static final String EMPLOYEE_POST_URL = "/employees";

	// with path param or request param
	public static final String EMPLOYEE_GET_URL = "/employee";

	// diffrentiate based on HTTTP method so no ambiguity/similarity between URLS
	public static final String EMPLOYEE_ADDR_REVISE = "/address";

	public static final String EMPLOYEE_ADDR_SAVE = "/addresses";

	public static final String GET_EMPLOYEE_ADDRESS = "/address";
	

}

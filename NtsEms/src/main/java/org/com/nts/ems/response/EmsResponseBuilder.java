/**
 * 
 */
package org.com.nts.ems.response;

import org.com.nts.ems.utility.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * class contains method to build the {@link EmsResponse} which will be return
 * as response of the incoming request
 * 
 * @author chetan
 *
 */
@Component
public class EmsResponseBuilder {

	@Autowired
	private EmsResponse emsResponse;

	/**
	 * <p>
	 * method to build response object
	 * 
	 * @param statusCode
	 * @param type
	 * @param description
	 * @param object
	 * @return
	 */
	public EmsResponse buildEmsResponse(String statusCode, StatusType type, String description, Object object) {

		/*
		 * emsResponse.setStatusCode(statusCode);
		 * emsResponse.setDescription(description); emsResponse.setStatusType(type);
		 * emsResponse.setDetails(object);
		 */
		/***
		 * logic for returning Response... as per Response class
		 */
		return emsResponse;
	}

}

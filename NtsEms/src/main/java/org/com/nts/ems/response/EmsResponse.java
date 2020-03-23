/**
 * 
 */
package org.com.nts.ems.response;

import org.com.nts.ems.utility.StatusType;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <p>
 * build the customize response for error,success and waring
 * 
 */
@Component
@Data
public class EmsResponse {

	private String statusCode; // final will be SU_EMP_201, SU_HR_101, EX_HR_202

	private StatusType statusType; //SU, EX, WN

	private String description; //message

	private Object details; // response data


}

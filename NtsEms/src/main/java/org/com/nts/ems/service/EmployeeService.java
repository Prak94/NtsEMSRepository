/**
 * 
 */
package org.com.nts.ems.service;

import org.com.nts.ems.model.EmployeeMasterModel;
import org.com.nts.ems.response.EmsResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author chetan
 *
 */
public interface EmployeeService {

	public ResponseEntity<EmsResponse> pushEmployee(EmployeeMasterModel model);

}

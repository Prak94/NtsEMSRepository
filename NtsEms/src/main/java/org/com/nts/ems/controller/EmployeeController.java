/**
 * 
 */
package org.com.nts.ems.controller;

import org.com.nts.ems.model.EmployeeMasterModel;
import org.com.nts.ems.response.EmsResponse;
import org.com.nts.ems.service.EmployeeService;
import org.com.nts.ems.utility.EmsRestUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chetan
 *
 */
@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = EmsRestUrl.EMPLOYEE_POST_URL)
	public ResponseEntity<EmsResponse> persistEmployeeMaster(@RequestBody EmployeeMasterModel employeeMasterModel) {
		logger.info("persistEmployeeMeaster() handler invoked");
		return employeeService.pushEmployee(employeeMasterModel);
	}

}

/**
 * 
 */
package org.com.nts.ems.service.impl;

import org.com.nts.ems.entity.EmployeeMaster;
import org.com.nts.ems.model.EmployeeMasterModel;
import org.com.nts.ems.repository.EmployeeRepository;
import org.com.nts.ems.response.EmsResponse;
import org.com.nts.ems.response.EmsResponseBuilder;
import org.com.nts.ems.service.EmployeeService;
import org.com.nts.ems.utility.EmployeeCodeGenerator;
import org.com.nts.ems.utility.StatusType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author chetan
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeCodeGenerator codeGenerator;

	@Autowired
	private EmsResponseBuilder responeBuilder;

	@Override
	public ResponseEntity<EmsResponse> pushEmployee(EmployeeMasterModel employeeMasterModel) {
		logger.info("pushEmployee() invoked");
		String eCode = "";
		try {
			EmployeeMaster employeeMaster = mapper.map(employeeMasterModel, EmployeeMaster.class);
			eCode = codeGenerator.getEmployeeCode();
			employeeMaster.setEmployeeCode(eCode);
			employeeRepository.save(employeeMaster);
		} catch (Exception e) {
			logger.error("error while persist employee", e);
			return new ResponseEntity<EmsResponse>(responeBuilder.buildEmsResponse("EX_CM_101", StatusType.ERROR,
					"Error while regsiter Employee", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmsResponse>(responeBuilder.buildEmsResponse("SU_EM_001", StatusType.SUCCESS,
				"successfully register employee with below code", eCode), HttpStatus.CREATED);
	}

}

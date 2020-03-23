/**
 * 
 */
package org.com.nts.ems.controller;

import java.util.List;

import org.com.nts.ems.model.AddressMasterModel;
import org.com.nts.ems.model.AddressViewModel;
import org.com.nts.ems.response.EmsResponse;
import org.com.nts.ems.service.AddressService;
import org.com.nts.ems.utility.EmsRestUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chetan
 *
 */
@RestController
public class AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@PostMapping(value = EmsRestUrl.EMPLOYEE_ADDR_SAVE)
	public ResponseEntity<EmsResponse> persistEmployeeAddress(
			@RequestParam(name = "empCode", required = true) String empCode,
			@RequestBody List<AddressMasterModel> addressMasterModels) {
		logger.info("persistEmployeeAddress() invoked.");
		return addressService.pushAddress(addressMasterModels, empCode);
	}

	@GetMapping(value = EmsRestUrl.GET_EMPLOYEE_ADDRESS)
	public ResponseEntity<EmsResponse> getEmployeeAddress(
			@RequestParam(name = "empCode", required = true) String empCode) {
		logger.info("getEmployeeAddress() invoked.");
		return addressService.getEmployeeAddress(empCode);
	}

	@PutMapping(value = EmsRestUrl.EMPLOYEE_ADDR_REVISE)
	public ResponseEntity<EmsResponse> reviseEmployeeAddress(@RequestBody AddressViewModel addressViewModels) {
		logger.info("reviseEmployeeAddress() invoked.");
		return addressService.updateEmployeeAddress(addressViewModels);
	}
}

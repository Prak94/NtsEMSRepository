/**
 * 
 */
package org.com.nts.ems.service;

import java.util.List;

import org.com.nts.ems.model.AddressMasterModel;
import org.com.nts.ems.model.AddressViewModel;
import org.com.nts.ems.response.EmsResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author chetan
 *
 */
public interface AddressService {

	public ResponseEntity<EmsResponse> pushAddress(List<AddressMasterModel> addressMasterModels, String empCodes);

	public ResponseEntity<EmsResponse> getEmployeeAddress(String empCode);

	public ResponseEntity<EmsResponse> updateEmployeeAddress(AddressViewModel addressViewModel);

}

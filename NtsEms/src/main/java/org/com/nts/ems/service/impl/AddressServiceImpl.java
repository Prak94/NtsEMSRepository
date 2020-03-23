/**
 * 
 */
package org.com.nts.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.com.nts.ems.entity.AddressMaster;
import org.com.nts.ems.entity.EmployeeMaster;
import org.com.nts.ems.model.AddressMasterModel;
import org.com.nts.ems.model.AddressViewModel;
import org.com.nts.ems.repository.AddressRepository;
import org.com.nts.ems.repository.EmployeeRepository;
import org.com.nts.ems.response.EmsResponse;
import org.com.nts.ems.response.EmsResponseBuilder;
import org.com.nts.ems.service.AddressService;
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
public class AddressServiceImpl implements AddressService {

	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private AddressRepository addressReposiotry;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmsResponseBuilder responseBuilder;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public ResponseEntity<EmsResponse> pushAddress(List<AddressMasterModel> addressMasterModels, String empCode) {
		logger.info("pushAddress() invoked.");
		try {

			List<AddressMaster> addressMasters = addressMasterModels.stream()
					.map(x -> mapper.map(x, AddressMaster.class)).collect(Collectors.toList());

			EmployeeMaster employeeMaster = employeeRepository.findByEmployeeCode(empCode);
			if (employeeMaster == null) {
				return new ResponseEntity<EmsResponse>(
						responseBuilder.buildEmsResponse("EX_EM_104", StatusType.ERROR, "Invalid Employee Code", null),
						HttpStatus.NOT_FOUND);
			}

			addressMasters.stream().forEach(x -> {
				x.setEmployeeAddress(employeeMaster);
			});
			addressReposiotry.saveAll(addressMasters);

		} catch (Exception e) {
			logger.error("error while persit address", e);
			return new ResponseEntity<EmsResponse>(
					responseBuilder.buildEmsResponse("EX_EM_103", StatusType.ERROR, "Error while saving address", null),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<EmsResponse>(
				responseBuilder.buildEmsResponse("SU_EM_002", StatusType.SUCCESS, "Successfully save address", null),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<EmsResponse> getEmployeeAddress(String empCode) {
		logger.info("getEmployeeAddress() invoked ");
		AddressViewModel addressViewModel = new AddressViewModel();
		try {
			// first validation call for emp by empCode
			List<AddressMaster> addMasters = addressReposiotry.findAllByEmployeeAddressEmployeeCode(empCode);
			addMasters.stream().forEach(x -> {
				System.out.println("" + x.getCity());
			});

			addressViewModel.setEmpCode(addMasters.get(0).getEmployeeAddress().getEmployeeCode());
			addressViewModel.setEmpName(addMasters.get(0).getEmployeeAddress().getFname());

			addressViewModel.setAddresses(
					addMasters.stream().map(x -> mapper.map(x, AddressMasterModel.class)).collect(Collectors.toList()));

		} catch (Exception e) {
			return new ResponseEntity<EmsResponse>(responseBuilder.buildEmsResponse("EX_EM_104", StatusType.ERROR,
					"Error while fethching address details", null), HttpStatus.CREATED);
		}
		return new ResponseEntity<EmsResponse>(responseBuilder.buildEmsResponse("SU_EM_004", StatusType.SUCCESS,
				"Successfully fetch address details", addressViewModel), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmsResponse> updateEmployeeAddress(AddressViewModel addressViewModel) {

		try {
			EmployeeMaster employeeMaster = employeeRepository.findByEmployeeCode(addressViewModel.getEmpCode());
			// check employee is null or not if null then return from here

			// collect all address of employee which in in database
			List<AddressMaster> addressList = employeeMaster.getAddressMasters();

			// collect all address from json payload including non empty id
			List<AddressMasterModel> inputModelList = addressViewModel.getAddresses();

			// convert all address model from json payload to list of entities
			List<AddressMaster> addressMasters = inputModelList.stream().map(x -> mapper.map(x, AddressMaster.class))
					.collect(Collectors.toList());

			// create empty list which contains update model
			List<AddressMaster> addressUpdateList = new ArrayList<AddressMaster>();

			// collect all address whose id is null that has to be persist
			List<AddressMaster> addresPersistList = addressViewModel.getAddresses().stream()
					.filter(x -> x.getAddressId() == null).map(x -> mapper.map(x, AddressMaster.class))
					.collect(Collectors.toList());

			// remove object from list whose id is null
			addressViewModel.getAddresses().removeIf(x -> x.getAddressId() == null);
			addressMasters.removeIf(x -> x.getAddressId() == null);

			if (addressList != null) {
				addressList.stream().forEach(x -> addressMasters.stream()
						.filter(y -> y.getAddressId().compareTo(x.getAddressId()) == 0).forEach(z -> {
							addressUpdateList.add(z);
						}));
			}
			//addressList get from database
			//addressMasters get from UI or JSON
			List<AddressMaster> deleteAddressList = addressList.stream().filter(x -> !addressMasters.contains(x))
					.collect(Collectors.toList());

			if (!addressUpdateList.isEmpty()) {
				for (AddressMaster addressMaster : addressUpdateList) {
					AddressMaster addressToUpdate = addressReposiotry.getOne(addressMaster.getAddressId());
					addressToUpdate.setCity(addressMaster.getCity());
					addressToUpdate.setPinCode(addressMaster.getPinCode());
					addressToUpdate.setType(addressMaster.getType());
					addressToUpdate.setValue(addressMaster.getValue());
					addressReposiotry.save(addressToUpdate);
				}
			}
			System.out.println(deleteAddressList.size());
			if (deleteAddressList != null) {
				for (AddressMaster addressMaster : deleteAddressList) {
					// stop remove call
					employeeMaster.getAddressMasters().remove(addressMaster);
					addressReposiotry.deleteById(addressMaster.getAddressId());
				}

			}

			if (addresPersistList != null) {
				for (AddressMaster addressMaster : addresPersistList) {
					employeeMaster.getAddressMasters().add(addressMaster);
					addressMaster.setEmployeeAddress(employeeMaster);
					addressReposiotry.save(addressMaster);
				}
			}

			employeeRepository.save(employeeMaster);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<EmsResponse>(responseBuilder.buildEmsResponse("EX_EM_105", StatusType.ERROR,
					"Error while updating address details", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmsResponse>(
				responseBuilder.buildEmsResponse("SU_EM_005", StatusType.SUCCESS, "Successfully update address", null),
				HttpStatus.OK);
	}

}

/**
 * 
 */
package org.com.nts.ems.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author chetan
 *
 */
@Data
public class AddressViewModel {

	private String empName;

	@NotBlank
	private String empCode;

	List<AddressMasterModel> addresses = new ArrayList<AddressMasterModel>();
}

/**
 * 
 */
package org.com.nts.ems.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @author chetan
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class AddressMasterModel {

	private Long addressId;

	@NotEmpty(message = "type.empty")
	@Length(min = 4, max = 7)
	@Pattern(regexp = "^(HOME|LOCAL|PERMANT|OFFICE)$", message = "type.message")
	private String type;

	@NotEmpty(message = "value.empty")
	@Length(min = 5, max = 200)
	private String value;

	@NotEmpty(message = "city.empty")
	@Length(min = 5, max = 20)
	private String city;

	@NotEmpty(message = "pinCode.empty")
	@Length(min = 6, max = 6)
	@Pattern(regexp = "^(0-9)+$", message = "pinCode.message")
	private String pinCode;

	//private AddressMasterModel employeeAddress;
}

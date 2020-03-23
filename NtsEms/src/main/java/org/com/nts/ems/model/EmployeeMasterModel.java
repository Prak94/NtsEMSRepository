/**
 * 
 */
package org.com.nts.ems.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

import lombok.Data;

/**
 * @author chetan
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class EmployeeMasterModel {

	@NotNull
	private Long empId;

	@NotEmpty
	@Length(min = 5, max = 20)
	@Pattern(regexp = "^[a-zA-z]+$", message = "fname.message")
	private String fname;

	@NotEmpty
	@Length(min = 5, max = 20)
	@Pattern(regexp = "^[a-zA-z]+$", message = "lname.message")
	private String lname;

	@NotEmpty
	@Length(min = 5, max = 20)
	@Pattern(regexp = "^[a-zA-z]+$", message = "middleName.message")
	private String middleName;

	private List<EmployeeMasterModel> addressMasters = new ArrayList<EmployeeMasterModel>();
}

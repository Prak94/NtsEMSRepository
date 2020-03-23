package org.com.nts.ems.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * 
 * @author chetan
 *
 */
@Entity
@Table(name = "EmployeeMaster")
@Data
public class EmployeeMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMP_ID")
	private Long empId;

	@Column(name = "FIRST_NAME")
	private String fname;

	@Column(name = "LAST_NAME")
	private String lname;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "EMP_CODE")
	private String employeeCode;

	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "employeeAddress")
	@JsonManagedReference(value = "emp_addr")
	private List<AddressMaster> addressMasters = new ArrayList<AddressMaster>();

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public List<AddressMaster> getAddressMasters() {
		return addressMasters;
	}

	public void setAddressMasters(List<AddressMaster> addressMasters) {
		this.addressMasters = addressMasters;
	}

}

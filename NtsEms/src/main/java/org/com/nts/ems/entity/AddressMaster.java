/**
 * 
 */
package org.com.nts.ems.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

/**
 * @author chetan
 *
 */
@Entity
@Table(name = "AddressMaster")
@Data
public class AddressMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private Long addressId;

	@Column(name = "ADDRESS_TYPE")
	private String type;

	@Column(name = "VALUE")
	private String value;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PINCODE")
	private String pinCode;

	@ManyToOne
	@JoinColumn(name = "ADDR_EMP_ID", referencedColumnName = "EMP_ID", unique = false)
	@JsonBackReference(value = "emp_addr")
	private EmployeeMaster employeeAddress;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressMaster other = (AddressMaster) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		return result;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public EmployeeMaster getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(EmployeeMaster employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	

}

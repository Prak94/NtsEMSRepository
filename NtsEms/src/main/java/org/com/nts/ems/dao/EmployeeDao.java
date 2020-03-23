/**
 * 
 */
package org.com.nts.ems.dao;

import org.com.nts.ems.custom.exception.EmsException;

/**
 * @author chetan
 *
 */
public interface EmployeeDao {

	public String generateEmployeeCode() throws EmsException;

}

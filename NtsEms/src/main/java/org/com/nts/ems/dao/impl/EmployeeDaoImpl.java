/**
 * 
 */
package org.com.nts.ems.dao.impl;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.com.nts.ems.custom.exception.EmsException;
import org.com.nts.ems.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author chetan
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Override
	public String generateEmployeeCode() throws EmsException {
		logger.info("generateEmployeeCode() invoked ");
		String empCode = "";
		try {
			String sql = "select e.employeeCode from EmployeeMaster e order by e.empId desc";
			empCode = (String) entityManager.createQuery(sql).setMaxResults(1).getSingleResult();

		} catch (NoResultException nre) {
			return empCode;
		} catch (Exception e) {
			logger.error("error while generating employee code", e);
			//throw new EmsException("error while generating emp code", "EX_EM_100");
		}
		return empCode;
	}

}

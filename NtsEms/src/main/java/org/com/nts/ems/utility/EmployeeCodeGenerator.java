/**
 * 
 */
package org.com.nts.ems.utility;

import org.com.nts.ems.custom.exception.EmsException;
import org.com.nts.ems.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * class contains method to generate employee code EX->NTS001 and so on
 * 
 * @author chetan
 *
 */
@Component
public class EmployeeCodeGenerator {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeCodeGenerator.class);

	@Autowired
	private EmployeeDao employeeDao;

	/**
	 * <p>
	 * method to generate next employee code
	 * 
	 * @return
	 * @throws EmsException
	 */
	public String getEmployeeCode() throws EmsException {
		logger.info("getEmployeeCode() invoked.");
		String actulEmployeeCode = "";
		try {
			actulEmployeeCode = employeeDao.generateEmployeeCode();
			if (actulEmployeeCode.isEmpty()) {
				actulEmployeeCode = "NTS0001";
			} else {
				int acutlInt = Integer.parseInt(actulEmployeeCode.substring(3));
				if (acutlInt < 100) {
					acutlInt += 1;
					if(acutlInt<10)
						actulEmployeeCode = "NTS000" + acutlInt;
					else
						actulEmployeeCode = "NTS00" + acutlInt;

				} else if (acutlInt >= 100 && acutlInt < 1000) {
					acutlInt += 1;
					actulEmployeeCode = "NTS00" + acutlInt;
				} else if (acutlInt >= 1000 && acutlInt < 10000) {
					acutlInt += 1;
					actulEmployeeCode = "NTS" + acutlInt;
				}
			}

		} catch (EmsException ems) {
			throw ems;
		} catch (Exception e) {
			logger.error("errro while getEmployeeCode", e);
		}
		return actulEmployeeCode;

	}

}

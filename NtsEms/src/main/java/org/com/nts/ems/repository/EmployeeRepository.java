/**
 * 
 */
package org.com.nts.ems.repository;

import org.com.nts.ems.entity.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chetan
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeMaster, Long> {

	public EmployeeMaster findByEmployeeCode(String empCode);

}

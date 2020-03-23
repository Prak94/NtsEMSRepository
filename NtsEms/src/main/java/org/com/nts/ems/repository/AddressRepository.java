/**
 * 
 */
package org.com.nts.ems.repository;

import java.util.List;

import org.com.nts.ems.entity.AddressMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chetan
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressMaster, Long> {

	public List<AddressMaster> findAllByEmployeeAddressEmployeeCode(String empCode);

}

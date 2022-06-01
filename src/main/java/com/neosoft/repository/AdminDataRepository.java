package com.neosoft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.entity.AdminData;
@Repository
public interface AdminDataRepository  extends JpaRepository<AdminData, Long>{

	Optional<AdminData> findByName(String name);

	AdminData findBySurname(String surname);

	Optional<List<AdminData>> findByPincode(String pincode);

	List<AdminData> findAllByOrderByDOBAsc();

	List<AdminData> findAllByOrderByDOJAsc();

	List<AdminData> findAllByOrderByDOJDesc();

	@Modifying
	@Transactional 
	@Query(value =  "UPDATE admin SET is_deleted = true WHERE id=?",nativeQuery = true)
	public void deleteId(long id);

}

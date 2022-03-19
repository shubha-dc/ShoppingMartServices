package com.infostretch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infostretch.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Transactional
	@Query(value = "SELECT * FROM Address a WHERE a.user_id=:userId", nativeQuery = true)
	List<Address> getProductsExistForCurrentUserId(@Param("userId") String userId);

}

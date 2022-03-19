package com.infostretch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infostretch.model.Address;
import com.infostretch.model.CentralWareHousee;


@Repository
public interface LocalWareHouseRepository extends JpaRepository<CentralWareHousee, Integer> {
	
	

}

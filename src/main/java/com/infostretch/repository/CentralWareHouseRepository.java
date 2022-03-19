package com.infostretch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infostretch.model.LocalWareHouse;


@Repository
public interface CentralWareHouseRepository extends JpaRepository<LocalWareHouse, Integer> {
	
	
	

}

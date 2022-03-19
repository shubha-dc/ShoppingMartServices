package com.infostretch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infostretch.model.PendingRequest;
import com.infostretch.model.Product;


@Repository
public interface PendingRequestsRepository extends JpaRepository<PendingRequest, Integer> {
	
	Boolean existsByProductId(Integer integer);
	
}

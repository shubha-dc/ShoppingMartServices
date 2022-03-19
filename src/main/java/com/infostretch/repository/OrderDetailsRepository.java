package com.infostretch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infostretch.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	@Transactional
	@Query(value = "FROM OrderDetails WHERE user_user_id = ?1")
	List<OrderDetails> getOrderdetailsByCurrentUser( String id);

}

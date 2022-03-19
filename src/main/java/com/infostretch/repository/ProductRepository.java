package com.infostretch.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infostretch.model.Product;
import com.infostretch.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<User> findByProductname(String productName);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE products c SET c.quantity = :Quantity WHERE c.product_id = :id", nativeQuery = true)
	int updateExistingProductQuantity(@Param("Quantity") String quantity, @Param("id") String id);
	
}

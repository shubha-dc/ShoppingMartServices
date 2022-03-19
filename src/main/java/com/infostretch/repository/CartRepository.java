package com.infostretch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infostretch.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Boolean existsByProductId(int productId);

	Boolean existsByUserId(int userId);
	
	@Transactional
	long deleteByUserId(Integer userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Cart c SET c.quantity = :Quantity WHERE c.cart_id = :id", nativeQuery = true)
	int updateExistingProductQuantity(@Param("Quantity") String quantity, @Param("id") String id);

	@Transactional
	@Query(value = "FROM Cart WHERE user_id = ?1")
	List<Cart> getCartProductsByCurrentUser( String id);
	
	@Transactional
	@Query(value = "SELECT * FROM Cart c WHERE c.product_id = :productId && c.user_id=:userId", nativeQuery = true)
	List<Cart> getProductsExistForCurrentUserId(@Param("productId") String productId, @Param("userId") String userId);

}

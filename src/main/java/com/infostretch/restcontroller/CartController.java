package com.infostretch.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.Cart;
import com.infostretch.model.Product;
import com.infostretch.repository.CartRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/cart/")
public class CartController {

	@Autowired
	CartRepository cartRepo;

	@PostMapping("/addtocart")
	public ResponseEntity<?> addProductToCart(@Valid @RequestBody Cart cart) {
		String message;
		List<Cart> productExistForCurrentUserId = cartRepo.getProductsExistForCurrentUserId(
				String.valueOf(cart.getProductId()), String.valueOf(cart.getUserId()));
		if (productExistForCurrentUserId.isEmpty() || productExistForCurrentUserId == null) {
			cartRepo.save(cart);
			message = "Added item to cart successfully";
		} else {
			message = "Already Item present in cart";
		}

		return ResponseEntity.ok(new MessageResponse(message));

	}

	@PostMapping("/updatecart/quantity={quantity}/id={cartId}")
	public ResponseEntity<?> updatedCartQuantity(@PathVariable(value = "quantity") String quantity,
			@PathVariable(value = "cartId") String cartId) {

		cartRepo.updateExistingProductQuantity(quantity, cartId);
		return ResponseEntity.ok("Updated product quantity successfully");
	}

	@GetMapping("/products/{userId}")
	public ResponseEntity<?> getProducts(@PathVariable(value = "userId") String userId) {

		List<Cart> list = cartRepo.getCartProductsByCurrentUser(userId);

		if (list.isEmpty() || list == null) {
			ResponseEntity.noContent();
		}

		return ResponseEntity.ok(list);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(value = "id") Integer cartId) {

		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Error: Product is not found in cart with Cart id::" + cartId));

		return ResponseEntity.ok(cart);

	}

	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Integer productId,
			@Valid @RequestBody Cart cartDetails) {
		Cart product = cartRepo.findById(productId).orElseThrow(
				() -> new RuntimeException("Error: Product is not found in cart with Cart id::" + productId));
		product.setProductname(cartDetails.getProductname());
		product.setBrand(cartDetails.getBrand());
		product.setPrice(cartDetails.getPrice());
		product.setProductId(cartDetails.getProductId());
		product.setQuantity(cartDetails.getQuantity());
		product.setUserId(cartDetails.getUserId());

		final Cart updatedCart = cartRepo.save(product);

		return ResponseEntity.ok(updatedCart);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable(value = "id") Integer productId) {

		Cart product = cartRepo.findById(productId).orElseThrow(
				() -> new RuntimeException("Error: Product is not found in cart with Cart id::" + productId));

		cartRepo.delete(product);

		return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));

	}
	
	@DeleteMapping("/removeall/{id}")
	public ResponseEntity<?> removeProductByCurrentUser(@PathVariable(value = "id") Integer userId) {
		
		cartRepo.deleteByUserId(userId);
		return null;
		
	}

}

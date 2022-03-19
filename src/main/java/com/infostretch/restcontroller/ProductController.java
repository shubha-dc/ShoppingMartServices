package com.infostretch.restcontroller;

import java.util.List;
import java.util.Optional;

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

import com.infostretch.model.Product;
import com.infostretch.repository.ProductRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepo;

	@GetMapping("/products")
	public ResponseEntity<?> getProducts() {

		List<Product> list = productRepo.findAll();

		if (list.isEmpty() || list == null) {
			return ResponseEntity.ok(new MessageResponse("No products found"));
		}

		return ResponseEntity.ok(list);
	}

	@PostMapping("/addproduct")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> addProducts(@Valid @RequestBody Product product) {

		productRepo.save(product);

		return ResponseEntity.ok(new MessageResponse("Product added successfully!"));

	}

	@DeleteMapping("/product/{id}")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> removeProduct(@PathVariable(value = "id") Integer productId) {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Error: Product is not found with product id::" + productId));

		productRepo.delete(product);

		return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));

	}

	@GetMapping("/product/{id}")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> getProduct(@PathVariable(value = "id") Integer productId) {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Error: Product is not found with product id::" + productId));

		return ResponseEntity.ok(product);

	}

	@PutMapping("/product/{id}")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Integer productId,
			@Valid @RequestBody Product productDetails) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Error: Product is not found with product id::" + productId));
		product.setProductname(productDetails.getProductname());
		product.setBrand(productDetails.getBrand());
		product.setPrice(productDetails.getPrice());
		product.setDescription(productDetails.getDescription());
		product.setImageFileName(productDetails.getImageFileName());

		final Product updatedProduct = productRepo.save(product);

		return ResponseEntity.ok(updatedProduct);
	}

	@PostMapping("/product/{id}/quantityby/{quantity}")
	public ResponseEntity<?> updatePlaceOrderDecreasedQuantity(@PathVariable(value = "id") String productId,
			@PathVariable(value = "quantity") String quantity) {

		Optional<Product> product = productRepo.findById(Integer.valueOf(productId));
		Integer oldQuantity = Integer.valueOf(product.get().getQuantity());

		if (oldQuantity > 0) {
			quantity = String.valueOf(oldQuantity - Integer.valueOf(quantity));

			try {
				productRepo.updateExistingProductQuantity(quantity, productId);
			} catch (Exception e) {
				return ResponseEntity.ok(new MessageResponse("Error while updating the quantity"));
			}
		}else {
			return ResponseEntity.ok(new MessageResponse("Product is not available for checkout"));
		}

		return ResponseEntity.ok(new MessageResponse("updated the quantity"));
	}
	
	@PostMapping("/product/{id}/quantity/{quantity}")
	public ResponseEntity<?> updateQuantity(@PathVariable(value = "id") String productId,
			@PathVariable(value = "quantity") String quantity) {

		Optional<Product> product = productRepo.findById(Integer.valueOf(productId));
		Integer oldQuantity = Integer.valueOf(product.get().getQuantity());

		
			quantity = String.valueOf(oldQuantity + Integer.valueOf(quantity));

			try {
				productRepo.updateExistingProductQuantity(quantity, productId);
			} catch (Exception e) {
				return ResponseEntity.ok(new MessageResponse("Error while updating the quantity"));
			}
		
		return ResponseEntity.ok(new MessageResponse("updated the quantity"));
	}
	
	
	
	

}

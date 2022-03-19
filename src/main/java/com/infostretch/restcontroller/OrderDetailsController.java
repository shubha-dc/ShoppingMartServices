package com.infostretch.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.OrderDetails;
import com.infostretch.repository.OrderDetailsRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/orderdetails/")
public class OrderDetailsController {

	@Autowired
	OrderDetailsRepository repo;

	@GetMapping("/products/{userId}")
	public ResponseEntity<?> getProducts(@PathVariable(value = "userId") String id) {

		List<OrderDetails> list = repo.getOrderdetailsByCurrentUser(id);

		if (list.isEmpty() || list == null) {
			ResponseEntity.noContent();
		}

		return ResponseEntity.ok(list);
	}

	@GetMapping("/products")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> getAllOrderDetails() {

		List<OrderDetails> list = repo.findAll();

		if (list.isEmpty() || list == null) {
			ResponseEntity.noContent();
		}
		
		

		return ResponseEntity.ok(list);
	}

	@PostMapping("/addproduct")
	@PreAuthorize("hasRole('SUPPLIER')")
	public ResponseEntity<?> addProducts(@Valid @RequestBody OrderDetails product) {

		repo.save(product);

		return ResponseEntity.ok(new MessageResponse("Product added in order details successfully!"));

	}

}

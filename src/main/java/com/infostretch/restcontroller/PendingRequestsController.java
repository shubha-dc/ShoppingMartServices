package com.infostretch.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.PendingRequest;
import com.infostretch.repository.PendingRequestsRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/pendingRequests/")
public class PendingRequestsController {

	@Autowired
	PendingRequestsRepository requestRepo;

	@PostMapping("/addRequest")
	public ResponseEntity<?> addRequest(@Valid @RequestBody PendingRequest request) {

		if (requestRepo.existsByProductId(request.getProduct().getId())) {
			return ResponseEntity.ok(new MessageResponse("Request already present"));
		} else {
			requestRepo.save(request);
			return ResponseEntity.ok(new MessageResponse("Request added successfully"));
		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> getRequests() {

		List<PendingRequest> list = requestRepo.findAll();

		if (list.isEmpty() || list == null) {
			return ResponseEntity.ok(new MessageResponse("No records found"));
		}

		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable(value = "id") Integer productId) {

		PendingRequest product = requestRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Error: Product is not found with id::" + productId));

		try {
			requestRepo.delete(product);
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Failed to delete with id" + productId));
		}

		return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));

	}

}

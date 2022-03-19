package com.infostretch.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.Address;
import com.infostretch.repository.AddressRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/address/")
public class AddressController {

	@Autowired
	AddressRepository addressRepo;

	@PostMapping("/addAddress")
	public ResponseEntity<?> addProductToCart(@Valid @RequestBody Address address) {

		addressRepo.save(address);
		return ResponseEntity.ok(new MessageResponse("ADDRESS SAVED SUCCESSFULLY"));

	}

	@GetMapping(value = { "/allAddress/{userId}" })
	public ResponseEntity<?> getAddresses(@PathVariable(value = "userId") String userId) {

		List<Address> list = addressRepo.getProductsExistForCurrentUserId(userId);

		if (list.isEmpty() || list == null) {
			ResponseEntity.notFound();
		}

		return ResponseEntity.ok(list);
	}

}

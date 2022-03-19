package com.infostretch.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.LocalWareHouse;
import com.infostretch.repository.CentralWareHouseRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/")
public class CentralController {
	
	@Autowired
	private CentralWareHouseRepository centralRepo;
	
	@GetMapping("/products/central")
	public ResponseEntity<?> getProducts() {

		List<LocalWareHouse> list = centralRepo.findAll();

		if (list.isEmpty() || list == null) {
			return	ResponseEntity.ok(new MessageResponse("No products found"));
		}

		return ResponseEntity.ok(list);
	}

}

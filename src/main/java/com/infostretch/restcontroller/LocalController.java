package com.infostretch.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.CentralWareHousee;
import com.infostretch.repository.LocalWareHouseRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/")
public class LocalController {

	@Autowired
	LocalWareHouseRepository localRepo;

	@GetMapping("/products/local")
	public ResponseEntity<?> getProduts() {

		List<CentralWareHousee> list = localRepo.findAll();

		if (list.isEmpty() || list == null) {
			return ResponseEntity.ok(new MessageResponse("No products found"));
		}

		return ResponseEntity.ok(list);
	}

}

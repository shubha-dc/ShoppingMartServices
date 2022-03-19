package com.infostretch.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.ERole;
import com.infostretch.model.Role;
import com.infostretch.repository.RoleRepository;
import com.infostretch.springjwt.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	RoleRepository roleRepo;

	@PostConstruct
	public void addAllRoles() {

		System.out.println("IRONMAN");
		Role user = new Role(1, ERole.ROLE_USER);
		Role manager = new Role(2, ERole.ROLE_MANAGER);
		Role supplier = new Role(3, ERole.ROLE_SUPPLIER);
		Role admin = new Role(4, ERole.ROLE_ADMIN);

		roleRepo.save(user);
		roleRepo.save(manager);
		roleRepo.save(supplier);
		roleRepo.save(admin);


	}

}

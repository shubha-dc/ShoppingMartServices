package com.infostretch.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/guestuser")
	public String guestUser() {
		return "guestuser welcome.";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER')")
	public String managerAccess() {
		return "Manager Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/supplier")
	@PreAuthorize("hasRole('SUPPLIER')")
	public String supplierAccess() {
		return "Supplier Board.";
	}
	
	
}

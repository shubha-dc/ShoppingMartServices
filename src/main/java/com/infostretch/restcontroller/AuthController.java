package com.infostretch.restcontroller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infostretch.model.ERole;
import com.infostretch.model.Role;
import com.infostretch.model.User;
import com.infostretch.repository.RoleRepository;
import com.infostretch.repository.UserRepository;
import com.infostretch.springjwt.payload.request.LoginRequest;
import com.infostretch.springjwt.payload.request.SignupRequest;
import com.infostretch.springjwt.payload.response.JwtResponse;
import com.infostretch.springjwt.payload.response.MessageResponse;
import com.infostretch.springjwt.security.jwt.JwtUtils;
import com.infostretch.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getFirstname(), userDetails.getLastname(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(),
				signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: admin Role is not found."));
					roles.add(adminRole);
					break;

				case "supplier":
					Role supplierRole = roleRepository.findByName(ERole.ROLE_SUPPLIER)
							.orElseThrow(() -> new RuntimeException("Error: supplier Role is not found."));
					roles.add(supplierRole);
					break;

				case "manager":
					Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Manager Role is not found."));
					roles.add(managerRole);
					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: User Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PutMapping("/addsupplier/{id}")
	public ResponseEntity<?> addRole(@PathVariable(value = "id") Integer userId) {

		Set<Role> roles = new HashSet<>();

		User user = userRepository.findById(userId).get();
		Set<Role> existingRoles = user.getRoles();

		for (Role role : existingRoles) {
			if (role.getName().equals(ERole.ROLE_SUPPLIER)) {
				return ResponseEntity.ok(new MessageResponse("Supplier Role already present"));
			}
		}

		roles.addAll(user.getRoles());

		Role supplierRole = roleRepository.findByName(ERole.ROLE_SUPPLIER)
				.orElseThrow(() -> new RuntimeException("Error: supplier Role is not found."));
		roles.add(supplierRole);

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Supplier Role added successfully!"));

	}

}

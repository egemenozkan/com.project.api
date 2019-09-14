package com.project.api.controller;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.project.api.data.mapper.UserAccountMapper;
import com.project.api.data.model.ApiResponse;
import com.project.api.data.model.SignUpRequest;
import com.project.common.model.User;

@RestController
public class AuthRestController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserAccountMapper userAccountMapper;
	@Autowired
	Gson gson;

	private static final Logger LOG = LogManager.getLogger(AuthRestController.class);

	@GetMapping("/principal")
	public Principal user(Principal principal) {
		LOG.error("MyPrincipal-MyApp {}", gson.toJson(principal));
		return principal;
	}

	@GetMapping("/api/test")
	public ResponseEntity<?> test() {
		// logger.error(":: result {}",
		// gson.toJson(userAccountMapper.findByUsername("admin")));
		// logger.error(":: result {}",
		// gson.toJson(userAccountMapper.findRolesByUserId(1L)));
		//
		// // String name =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		String msg = String.format("Hello %s", "test");
		return new ResponseEntity<Object>(msg, HttpStatus.OK);
	}

	@GetMapping("/api/do")
	public ResponseEntity<?> hello() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String msg = String.format("Hello %s", name);
		return new ResponseEntity<Object>(msg, HttpStatus.OK);
	}

	@GetMapping(path = "/api/me", produces = "application/json")
	public User me() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new User();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userAccountMapper.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		// User user = new User(signUpRequest.getFirstName(),
		// signUpRequest.getLastName(), signUpRequest.getEmail(),
		// signUpRequest.getPassword(), signUpRequest.getUserType());
		User user = new User();
	//	user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		// .orElseThrow(() -> new AppException("User Role not set."));

		// user.setRoles(Collections.singleton(userRole));

		User result = userAccountMapper.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}

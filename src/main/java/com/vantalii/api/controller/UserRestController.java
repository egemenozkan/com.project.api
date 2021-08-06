package com.vantalii.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.common.model.User;
import com.vantalii.data.service.IUserService;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserRestController {
	@Autowired
	private IUserService userService;

	@PostMapping(value = "/users")
	public ResponseEntity<Long> createUser(RequestEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		long userId = userService.createUser(user);

		return new ResponseEntity<>(userId, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/users/register")
	public ResponseEntity<Long> register(RequestEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		long userId = userService.createUser(user);
		return new ResponseEntity<>(userId, HttpStatus.CREATED);
	}

	@PutMapping(value = "/users")
	public ResponseEntity<User> updateUser(RequestEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		user = userService.updateUser(user);
		ResponseEntity<User> response = null;
		if (user != null) {
			response = new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			response = new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> findAllUsers() {
		UserSearchRequest userSearchRequest = new UserSearchRequest();
		List<User> users = userService.findAllUsers(userSearchRequest);
		ResponseEntity<List<User>> response = null;
		if (users != null) {
			response = new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<User>>(users, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

//	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//	public ResponseEntity<User> updateUser(@PathVariable Long id) {
//		User user = userService.getUserById(id);
//		ResponseEntity<User> response = null;
//		if (user != null) {
//			response = new ResponseEntity<User>(user, HttpStatus.OK);
//		} else {
//			response = new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return response;
//	}
	
	@GetMapping(value = "/users/email")
	public ResponseEntity<User> getUsetByUsernameOrEmail(@RequestParam(required = true) String email) {
		User user = userService.getUserByEmailOrUsername(email);
		ResponseEntity<User> response = null;
		response = new ResponseEntity<>(user, HttpStatus.OK);

		return response;
	}

	@GetMapping(value = "/users/available/{emailOrUsername:.+}}/")
	public ResponseEntity<Boolean> userAvailableByEmailOrUsername(@PathVariable String emailOrUsername) {
		Boolean userExists = userService.existsByEmailOrUsername(emailOrUsername);
		Boolean available = userExists != null;

		ResponseEntity<Boolean> response = new ResponseEntity<>(available, HttpStatus.OK);

		return response;
	}

	@PostMapping(value = "/users/social")
	public ResponseEntity<User> updateSocialUser(RequestEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		userService.updateSocialUserByEmail(user);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	// @RequestMapping(value = "/users", method=RequestMethod.GET)
	// public ResponseEntity<List<User>> getUsers() {
	// List<User> users = userService.getUsersByUserType(UserType.ALL);
	// ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(users,
	// HttpStatus.OK);
	//
	// return response;
	// }
	//
	// @RequestMapping(value = "/users/{userId}", method=RequestMethod.GET)
	// public ResponseEntity<User> getUser(@PathVariable Long userId) {
	// User user = userService.getUserByUserId(userId);
	// ResponseEntity<User> response = new ResponseEntity<User>(user,
	// HttpStatus.OK);
	//
	// return response;
	// }
	//
	// @RequestMapping(value = "/users/types", method=RequestMethod.GET)
	// public ResponseEntity<List<UserType>> getUserTypes() {
	// List<UserType> userTypes = new
	// ArrayList<UserType>(EnumSet.allOf(UserType.class));
	// ResponseEntity<List<UserType>> response = new
	// ResponseEntity<List<UserType>>(userTypes, HttpStatus.OK);
	//
	// return response;
	// }
	//
	//
	// @RequestMapping(value = "/users/types/{userType}", method=RequestMethod.GET)
	// public ResponseEntity<List<User>> getUsers(@PathVariable int userType) {
	// List<User> users =
	// userService.getUsersByUserType(UserType.getById(userType));
	// ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(users,
	// HttpStatus.OK);
	//
	// return response;
	// }
	//
}

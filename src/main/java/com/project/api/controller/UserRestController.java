package com.project.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.api.data.service.IUserService;
import com.project.common.model.User;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserRestController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Long> createUser(RequestEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		long userId = userService.createUser(user);
		ResponseEntity<Long> response = new ResponseEntity<Long>(userId, HttpStatus.CREATED);

		return response;
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
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

	@RequestMapping(value = "/users", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/users/{usernameOrEmail}/", method = RequestMethod.GET)
	public ResponseEntity<User> getUsetByUsernameOrEmail(@PathVariable String usernameOrEmail) {
		User user = userService.getUserByEmailOrUsername(usernameOrEmail);
		ResponseEntity<User> response = null;
		if (user != null) {
			response = new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			response = new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(value = "/users/available/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> userAvailableByEmail(@PathVariable String email) {
		Boolean userExists = userService.existsByEmail(email);
		Boolean available = ((userExists == Boolean.FALSE) ? Boolean.TRUE : Boolean.FALSE);

		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(available, HttpStatus.OK);

		return response;
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

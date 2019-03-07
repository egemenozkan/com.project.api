package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.api.data.mapper.UserMapper;
import com.project.api.data.model.user.UserSearchRequest;
import com.project.api.data.service.IUserService;
import com.project.common.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Long createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userMapper.createUser(user);
		return user.getId();
	}

	@Override
	public User updateUser(User user) {
		if (userMapper.updateUser(user) > 0) {
			return getUserById(user.getId());
		}
		;
		return null;
	}

	@Override
	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userMapper.existsByEmail(email);
	}

	@Override
	public User getUserByEmailOrUsername(String emailOrUsername) {
		return userMapper.getUserByEmailOrUsername(emailOrUsername);
	}

	@Override
	public List<User> findAllUsers(UserSearchRequest userSearchRequest) {
		List<User> users = userMapper.findAllUsers(userSearchRequest);
		return users;
	}

}

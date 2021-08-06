package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.common.model.User;
import com.vantalii.api.data.mapper.UserMapper;
import com.vantalii.data.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserMapper userMapper;

//	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public long createUser(User user) {
		if (user != null && user.getPassword() != null && !user.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if (user !=null) {
			userMapper.createUser(user);
			return user.getId();
		}

		return 0;
	}

	@Override
	public User updateUser(User user) {
		if (userMapper.updateUser(user) > 0) {
			return getUserById(user.getId());
		}
		return null;
	}

	@Override
	public User getUserById(long id) {
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
		return userMapper.findAllUsers(userSearchRequest);
	}

	@Override
	public boolean existsByEmailOrUsername(String emailOrUsername) {
		return userMapper.existsByEmailOrUsername(emailOrUsername);
	}

	@Override
	public int updateSocialUserByEmail(User user) {
		userMapper.updateSocialUserByEmail(user);
		return 0;
	}

}

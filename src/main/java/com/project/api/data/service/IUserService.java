package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.common.model.User;

public interface IUserService {
	long createUser(User user);

	List<User> findAllUsers(UserSearchRequest userSearchRequest);

	User updateUser(User user);

	User getUserById(long id);

	User getUserByEmailOrUsername(String emailOrUsername);

	boolean existsByEmail(String email);

	boolean existsByEmailOrUsername(String emailOrUsername);
	
	int updateSocialUserByEmail(User user);

}

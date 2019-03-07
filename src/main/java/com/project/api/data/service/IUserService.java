package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.user.UserSearchRequest;
import com.project.common.model.User;

public interface IUserService {
    Long createUser(User user);

	List<User> findAllUsers(UserSearchRequest userSearchRequest);
    User updateUser(User user);
        
    User getUserById(Long id);
    
    User getUserByEmailOrUsername(String emailOrUsername);
        
    boolean existsByEmail(String email);
    
}

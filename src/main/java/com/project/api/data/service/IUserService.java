package com.project.api.data.service;

import com.project.common.model.User;

public interface IUserService {
    Long createUser(User user);
    
    User updateUser(User user);
        
    User getUserById(Long id);
    
    User getUserByEmailOrUsername(String emailOrUsername);
        
    boolean existsByEmail(String email);
    
}

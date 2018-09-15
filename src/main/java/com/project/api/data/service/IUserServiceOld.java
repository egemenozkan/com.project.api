package com.project.api.data.service;

import java.util.List;

import com.project.api.data.enums.UserType;
import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.Email;
import com.project.api.data.model.common.Phone;
import com.project.api.data.model.common.User;

public interface IUserServiceOld {
    // UserAccount loadUserByUsername(String username, UserType userType);
    // UserAccount loadUserByUsername(String username);
    List<User> getUsersByUserType(UserType userType);

    User getUserByUserId(Long userId);

    int createUser(User user);
    
    
    

    List<Phone> getPhonesByUserId(int userId);

    List<Email> getEmailsByUserId(int userId);

    List<Address> getAddressesByUserId(int userId);

    Email saveEmail(int userId, Email email);

    Phone savePhone(int userId, Phone phone);

    Address saveAddress(int userId, Address address);
    

}

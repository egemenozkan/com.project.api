package com.project.common.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.api.data.enums.UserType;

public class User implements UserDetails {

    private static final long serialVersionUID = 5577192456921820944L;

    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
//	private Company company;

    private List<String> roles;

    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

    public User() {
	this.accountNonExpired = true;
	this.accountNonLocked = true;
	this.credentialsNonExpired = true;
	this.enabled = true;
    }
    
    public  User(String firstName, String lastName, String email, String password, UserType userType) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.userType = userType;
    }

    @Override
    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
	return enabled;
    }

    public void grantAuthority(String authority) {
	if (roles == null)
	    roles = new ArrayList<>();
	roles.add(authority);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<>();
	if (roles != null) {
	    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

	}
	return authorities;
    }

    public List<String> getRoles() {
	return roles;
    }

    public void setRoles(List<String> roles) {
	this.roles = roles;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
	this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public UserType getUserType() {
	return userType;
    }

    public void setUserType(UserType userType) {
	this.userType = userType;
    }
}

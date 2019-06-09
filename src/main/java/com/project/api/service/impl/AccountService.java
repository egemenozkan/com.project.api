package com.project.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.api.data.service.IUserService;
import com.project.common.model.User;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Autowired
	private Gson gson;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

	@Override
	public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
		User user = userService.getUserByEmailOrUsername(emailOrUsername);
		if (user != null) {
			user.setPassword(passwordEncoder.encode("pw"));
			user.grantAuthority("ROLE_MYEDITOR");
			List<String> roles = new ArrayList<String>();
			roles.add("ADMIN");
			roles.add("EDITOR");
			roles.add("USER");
			user.setRoles(roles);
			LOGGER.debug("user-op {}", gson.toJson(user));
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", emailOrUsername));
		}
	}

	public User findAccountByUsername(String emailOrUsername) throws UsernameNotFoundException {
		User user = userService.getUserByEmailOrUsername(emailOrUsername);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", emailOrUsername));
		}

	}

	public Long register(User user) throws AccountException {
		if (!userService.existsByEmail(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userService.createUser(user);
		} else {
			throw new AccountException(String.format("Username[%s] already taken.", user.getUsername()));
		}
	}

	// @Transactional // To successfully remove the date @Transactional annotation
	// must be added
	// public void removeAuthenticatedAccount() throws UsernameNotFoundException {
	// String username =
	// SecurityContextHolder.getContext().getAuthentication().getName();
	// UserAccount acct = findAccountByUsername(username);
	// userAccountService.deleteAccountById(acct.getId());
	//
	// }
}

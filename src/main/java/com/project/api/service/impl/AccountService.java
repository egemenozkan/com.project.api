package com.project.api.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.api.data.service.IUserService;
import com.project.common.model.User;
import com.project.common.model.UserPrincipal;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Autowired
	private Gson gson;


	private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

	@Override
	public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
		User user = userService.getUserByEmailOrUsername(emailOrUsername);
		if (user != null) {
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
//			user.setPassword(passwordEncoder.encode("pw"));
//			List<String> roles = new ArrayList<String>();
//			roles.add("ROLE_ADMIN");
//			roles.add("ROLE_USER");
//			user.setRoles(roles);
			LOGGER.debug("user-op {}", gson.toJson(user));
			List<GrantedAuthority> authorities = new ArrayList<>();
			if (user.getRoles() != null) {
				user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
			}

//			return new UserPrincipal(user.getId(), user.getFirstName(), user.getLastName(),
//					user.getEmail(), user.getPictureUrl(), user.getUsername(), user.getPassword(), enabled,
//					accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return null;
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", emailOrUsername));
		}
	}

//	public User findAccountByUsername(String emailOrUsername) throws UsernameNotFoundException {
//		User user = userService.getUserByEmailOrUsername(emailOrUsername);
//		if (user != null) {
//			return user;
//		} else {
//			throw new UsernameNotFoundException(String.format("Username[%s] not found", emailOrUsername));
//		}
//
//	}
//
//	public Long register(User user) throws AccountException {
//		if (!userService.existsByEmail(user.getEmail())) {
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//			return userService.createUser(user);
//		} else {
//			throw new AccountException(String.format("Username[%s] already taken.", user.getUsername()));
//		}
//	}

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

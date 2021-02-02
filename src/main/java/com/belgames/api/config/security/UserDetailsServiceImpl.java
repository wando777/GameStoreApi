package com.belgames.api.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.belgames.api.config.security.user.User;
import com.belgames.api.config.security.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		List<User> user = userRepository.findByEmail(email);
		
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		User usuario = user.get(0);
		
		return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getPassword(), usuario.getPerfis());
	}

}

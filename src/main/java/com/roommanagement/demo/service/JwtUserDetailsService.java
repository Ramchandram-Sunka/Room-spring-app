package com.roommanagement.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roommanagement.demo.repository.RoomUserRepository;
import com.roommanagement.demo.repository.model.MyUserDetails;
import com.roommanagement.demo.repository.model.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	RoomUserRepository RoomUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = RoomUserRepository.findById(username);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

		return user.map(MyUserDetails::new).get();
	}

}
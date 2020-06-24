package com.roommanagement.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.roommanagement.demo.model.RoomUser;
import com.roommanagement.demo.repository.RoomUserRepository;
import com.roommanagement.demo.repository.model.User;

@Service
public class RoomService {

	@Autowired
	private RoomUserRepository roomUserRepository;

	public Map<String, User> createUser(RoomUser roomUser) {

		User user = new User();
		user.setFirstName(roomUser.getFirstName());
		user.setLastName(roomUser.getLastName());
		user.setGender(roomUser.getGender());
		user.setEmail(roomUser.getEmail());
		user.setPhoneNumber(roomUser.getPhoneNumber());
		user.setPwd(roomUser.getPwd());
		Map<String, User> map;
		User savedUser;

		if (roomUserRepository.existsById(user.getEmail())) {
			System.out.println("=============Already Exists=========> ");
			map = new HashMap<String, User>();
			map.put("DataIntegrityViolationException", null);
			return map;
		}

		try {

			savedUser = roomUserRepository.save(user);
			map = new HashMap<String, User>();
			map.put("Created", savedUser);
			return map;
		} catch (DataIntegrityViolationException e) {
			System.out.println("======================> " + e.getClass().getName());
			map = new HashMap<String, User>();
			map.put("DataIntegrityViolationException", null);
			return map;
		} catch (Exception e) {
			map = new HashMap<String, User>();
			map.put("DatabaseException", null);
			System.out.println("======================> " + e.getClass().getName());
			return map;
		}

	}
}

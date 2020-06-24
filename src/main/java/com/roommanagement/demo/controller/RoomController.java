package com.roommanagement.demo.controller;

import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roommanagement.demo.config.JwtTokenUtil;
import com.roommanagement.demo.model.Item;
import com.roommanagement.demo.model.JwtRequest;
import com.roommanagement.demo.model.JwtResponse;
import com.roommanagement.demo.model.RoomUser;
import com.roommanagement.demo.repository.model.User;
import com.roommanagement.demo.service.ItemService;
import com.roommanagement.demo.service.RoomService;

@CrossOrigin
@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	ItemService ItemService;

	@PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody RoomUser roomUser) {
		Map<String, User> userWithResponse;

		userWithResponse = roomService.createUser(roomUser);
		User savedUser = null;
		for (Map.Entry<String, User> entry : userWithResponse.entrySet()) {
			if ("Created".equals(entry.getKey())) {
				savedUser = entry.getValue();
				return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
			} else if ("DataIntegrityViolationException".equals(entry.getKey())) {
				savedUser = entry.getValue();
				return new ResponseEntity<>(savedUser, HttpStatus.CONFLICT);
			} else {
				savedUser = entry.getValue();

			}
		}
		return new ResponseEntity<>(savedUser, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPwd());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping(path = "/addItem", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addItem(HttpServletRequest request, @RequestBody Item item) {
		System.out.println("Date ======> " + item.getDateOfPurchase());
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			System.out.println("Header  " + header);
			System.out.println("Value  " + request.getHeader(header));
		}
		ItemService.addItem(item);
		return ResponseEntity.ok("success");
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(ItemService.getAllItems(), HttpStatus.OK);

	}

}

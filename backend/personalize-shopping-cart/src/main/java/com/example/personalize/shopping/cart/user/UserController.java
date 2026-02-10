package com.example.personalize.shopping.cart.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		userService.createUser(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {

		User userByUsername = userService.getUserByUsername(user.getUsername());

		boolean isAuthenticated = userService.authenticate(user);
		return isAuthenticated ?
				ResponseEntity.status(HttpStatus.OK)
						.body(String.valueOf(userByUsername.getId())) :
				ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Login failed");
	}
}

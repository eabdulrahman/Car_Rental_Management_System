package com.example.personalize.shopping.cart.Security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalize.shopping.cart.user.User;
import com.example.personalize.shopping.cart.user.UserRepository;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
public class AuthController {

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDateOfBirth(request.getDateOfBirth()); // Should be of type `LocalDate`
        user.setDrivingLicenseNumber(request.getDrivingLicenseNumber());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setZipCode(request.getZipCode());
        user.setState(request.getState());

        userRepository.save(user);


        return ResponseEntity.ok("User registered successfully!");
    }

    /* 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String token = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    */


//    @PostMapping("/login")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate the JWT token
        String token = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());

        // Create and attach HttpOnly Cookie
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
            .httpOnly(true)
            .secure(true) // Only if HTTPS is used
            .path("/")
            .maxAge(3600) // 1 day
            //.sameSite("Strict")
            .sameSite("None")
            .build();

        // Set the cookie in the response header
        response.setHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        
        //return ResponseEntity.ok()
        //.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        //.body("Login successful");
        

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userDetails = (User) authentication.getPrincipal();

        // You can customize the returned data
        return ResponseEntity.ok(Map.of(
            "userID", userDetails.getId(),
            "username", userDetails.getUsername(),
            "roles", userDetails.getAuthorities()
        ));
    }

}

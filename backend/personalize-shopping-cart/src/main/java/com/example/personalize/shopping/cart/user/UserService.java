package com.example.personalize.shopping.cart.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers(){
        return userRepository.findAll();
    }

	public User updateUser(User user) {
		User existingUser = userRepository.findById(user.getId()).orElse(null);
		if (existingUser != null) {
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setRole(user.getRole());
			
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setEmailAddress(user.getEmailAddress());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			existingUser.setDateOfBirth(user.getDateOfBirth());
			existingUser.setAddress(user.getAddress());
			existingUser.setCity(user.getCity());
			existingUser.setZipCode(user.getZipCode());
			existingUser.setState(user.getState());

			return userRepository.save(existingUser);
		}
		return null;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public boolean authenticate(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (existingUser != null) {
			return existingUser.getPassword().equals(user.getPassword());
		}
		return false;
	}
}

package com.example.demo;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().checkPassword(password)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validar(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			return true;
		} else {
			return false;
		}
    }
  

}

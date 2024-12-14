package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.models.User;
import springboot.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for UserRepository and PasswordEncoder
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user by encoding the password
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // Delete a user by ID
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Update user's company account
    public void updateCompanyAccount(User user) {
        if (user != null && user.getCompany() != null) {
            userRepository.updateCompanyAccount(user.getId(), user.getCompany());
        } else {
            throw new IllegalArgumentException("User or linked company cannot be null");
        }
    }
}

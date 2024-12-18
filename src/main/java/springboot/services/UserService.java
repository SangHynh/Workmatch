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

    public User registerUser(User user) {
        // Kiểm tra nếu email đã tồn tại trong hệ thống
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
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

    // Update user's candidate account
    public void updateCandidateAccount(User user) {
        if (user != null && user.getCandidate() != null) {
            userRepository.updateCandidateAccount(user.getId(), user.getCandidate());
        } else {
            throw new IllegalArgumentException("User or linked candidate cannot be null");
        }
    }

    // Lấy email của User bằng companyId
    public String getEmailByCompanyId(Long companyId) {
        // Lấy email của User qua companyId
        return userRepository.findEmailByCompanyId(companyId);
    }

    // Lấy email của User bằng candidateId
    public String getEmailByCandidateId(Long candidateId) {
        // Lấy email của User qua companyId
        return userRepository.findEmailByCandidateId(candidateId);
    }
}

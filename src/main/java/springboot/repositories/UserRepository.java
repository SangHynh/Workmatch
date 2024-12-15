package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import springboot.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.company = :company WHERE u.id = :userId")
    void updateCompanyAccount(Long userId, springboot.models.Company company);

    // Truy vấn qua companyId để lấy email của user
    @Query("SELECT u.email FROM User u WHERE u.company.id = :companyId AND u.role = springboot.enums.Role.COMPANY")
    String findEmailByCompanyId(Long companyId);


}
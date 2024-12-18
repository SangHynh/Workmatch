package springboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springboot.enums.Role;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    // Quan hệ với Company chỉ khi role là COMPANY
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "comp_id")
    private Company company;

    // Quan hệ với Candidate chỉ khi role là CANDIDATE
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    // Phương thức kiểm tra xem người dùng có role là COMPANY không
    public boolean isCompany() {
        return this.role == Role.COMPANY;
    }

    // Phương thức kiểm tra xem người dùng có role là CANDIDATE không
    public boolean isCandidate() {
        return this.role == Role.CANDIDATE;
    }

    // Phương thức kiểm tra xem người dùng có role là ADMIN không
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}

package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
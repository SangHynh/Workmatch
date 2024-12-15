package springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Page<Company> findByCompNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String compName, String address, Pageable pageable);

}
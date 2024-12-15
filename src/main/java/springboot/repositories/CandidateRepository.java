package springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.models.Candidate;
import springboot.models.Company;

import java.time.LocalDate;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // tìm theo tên hoặc địa chỉ
    Page<Candidate> findByFullNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String fullName, String address, Pageable pageable);

    // Lọc ứng viên theo thành phố (thông qua cityId)
    Page<Candidate> findByCityId(Long cityId, Pageable pageable);

    // Tìm kiếm ứng viên theo tên và thành phố (thông qua cityId)
    Page<Candidate> findByFullNameContainingIgnoreCaseAndCityId(String fullName, Long cityId, Pageable pageable);
}


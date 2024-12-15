package springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // tìm theo tên hoặc địa chỉ
    Page<Company> findByCompNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String compName, String address, Pageable pageable);

    // Lọc công ty theo thành phố (thông qua cityId)
    Page<Company> findByCityId(Long cityId, Pageable pageable);

    // Tìm kiếm công ty theo tên và thành phố (thông qua cityId)
    Page<Company> findByCompNameContainingIgnoreCaseAndCityId(String name, Long cityId, Pageable pageable);

}
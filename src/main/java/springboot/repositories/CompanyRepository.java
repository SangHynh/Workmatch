package springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.models.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // tìm theo tên hoặc địa chỉ
    Page<Company> findByCompNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String compName, String address, Pageable pageable);

    // Lọc công ty theo thành phố (thông qua cityId)
    Page<Company> findByCityId(Long cityId, Pageable pageable);

    // Tìm kiếm công ty theo tên và thành phố (thông qua cityId)
    Page<Company> findByCompNameContainingIgnoreCaseAndCityId(String name, Long cityId, Pageable pageable);

    // Lấy các công ty ngẫu nhiên với tối đa 5 công ty
    @Query("SELECT c FROM Company c ORDER BY RAND()")
    List<Company> findRandomCompanies(@Param("limit") int limit);


}
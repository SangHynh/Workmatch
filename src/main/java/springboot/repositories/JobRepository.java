package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import springboot.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Tìm kiếm công việc theo tên công việc
    Page<Job> findByJobNameContaining(String jobName, Pageable pageable);

    // Tìm kiếm công việc theo mô tả công việc
    Page<Job> findByJobDescContaining(String jobDesc, Pageable pageable);

    // Tìm kiếm công việc theo tên công ty thông qua đối tượng Company (Sử dụng @Query)
    @Query("SELECT j FROM Job j WHERE j.company.compName LIKE %?1%")
    Page<Job> findByCompanyNameContaining(String companyName, Pageable pageable);

    // Tìm tất cả công việc theo công ty
    Page<Job> findByCompanyId(Long companyId, Pageable pageable);
}

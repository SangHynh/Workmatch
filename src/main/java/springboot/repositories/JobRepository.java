package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.models.Job;

import java.util.List;

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


    // Tìm kiếm công việc theo tên công việc và tên công ty
    @Query("SELECT j FROM Job j WHERE j.jobName LIKE %:jobName% AND j.company.compName LIKE %:companyName%")
    Page<Job> findByJobNameContainingAndCompany_CompNameContaining(@Param("jobName") String jobName,
                                                                   @Param("companyName") String companyName,
                                                                   Pageable pageable);

    // Lấy ngẫu nhiên 6 công việc
    @Query("SELECT j FROM Job j ORDER BY RAND()")
    List<Job> findRandomJobs(Pageable pageable);
}

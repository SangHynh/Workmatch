package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.models.Job;
import springboot.repositories.CompanyRepository;
import springboot.repositories.JobRepository;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    /* Get all jobs */
    public Page<Job> getAllJobs(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findAll(pageable);
    }

    /* Lấy danh sách công việc theo jobName và companyName */
    public Page<Job> getAllJobsByJobNameAndCompanyName(String jobName, String companyName, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findByJobNameContainingAndCompany_CompNameContaining(jobName, companyName, pageable);
    }

    public Page<Job> getJobsByCompanyId(Long companyId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findByCompanyId(companyId, pageable);  // Filter by company ID
    }

    /* Search jobs by job name */
    public Page<Job> searchJobs(String searchQuery, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findByJobNameContaining(searchQuery, pageable);
    }

    /* Search jobs by company name */
    public Page<Job> searchJobsByCompanyName(String searchQuery, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findByCompanyNameContaining(searchQuery, pageable);
    }

    /* Search jobs by job description */
    public Page<Job> searchJobsByDescription(String searchQuery, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findByJobDescContaining(searchQuery, pageable);
    }

    /* Save job */
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    /* Update job */
    public void updateJob(Job job) {
        if (job.getId() != null) {
            jobRepository.save(job);
        }
    }

    /* Delete job */
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }


    /* Lấy ngẫu nhiên 6 công việc gợi ý */
    public List<Job> getJobSuggestions() {
        Pageable pageable = PageRequest.of(0, 6);  // Lấy 6 công việc ngẫu nhiên
        return jobRepository.findRandomJobs(pageable);
    }
}

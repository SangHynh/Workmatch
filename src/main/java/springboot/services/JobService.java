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
}

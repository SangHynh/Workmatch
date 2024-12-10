package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
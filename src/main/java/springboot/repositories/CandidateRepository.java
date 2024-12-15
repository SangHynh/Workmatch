package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.models.Candidate;

import java.time.LocalDate;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}

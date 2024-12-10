package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ids.CandidateSkillId;
import springboot.models.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
}
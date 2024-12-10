package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ids.JobSkillId;
import springboot.models.JobSkill;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}
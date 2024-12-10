package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
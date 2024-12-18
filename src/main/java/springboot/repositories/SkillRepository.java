package springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Page<Skill> findAll(Pageable pageable);
    Page<Skill> findBySkillNameContainingOrSkillDescriptionContaining(String skillName, String skillDescription, Pageable pageable);

}
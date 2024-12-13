package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.models.Skill;
import springboot.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    /* Get all skill */
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    /* Save skill */
    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    /* Update skill */
    public void updateSkill(Skill skill) {
        if (skill.getId() != null) {
            skillRepository.save(skill);
        }
    }

    /* Delete skill */
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }



}

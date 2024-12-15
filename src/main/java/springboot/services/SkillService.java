package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.models.Skill;
import springboot.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    /* Get all skill */
    public Page<Skill> getAllSkills(int pageNo, int pageSize, String sortBy,
                                    String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return skillRepository.findAll(pageable);
    }

    /* Search skill */
    public Page<Skill> searchSkills(String searchQuery, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return skillRepository.findBySkillNameContainingOrSkillDescriptionContaining(searchQuery, searchQuery, pageable);
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

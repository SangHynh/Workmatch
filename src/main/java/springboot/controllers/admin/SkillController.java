package springboot.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.models.Skill;
import springboot.services.SkillService;

@Controller
@RequestMapping("/admin/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    /* Get list skills */
    @GetMapping
    public String skill(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("skill", new Skill()); // Form thêm mới kỹ năng
        return "admin/pages/skill";
    }

    /* Add skill */
    @PostMapping("/add")
    public String addSkill(@ModelAttribute("skill") Skill skill) {
        skillService.saveSkill(skill);
        return "redirect:/admin/skill";
    }

    @PostMapping("/update")
    public String updateSkill(@ModelAttribute("skill") Skill skill) {
        skillService.updateSkill(skill);
        return "redirect:/admin/skill";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        skillService.deleteSkill(id);
        redirectAttributes.addFlashAttribute("message", "Xóa kỹ năng thành công!");
        return "redirect:/admin/skill";
    }

}

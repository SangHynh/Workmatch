package springboot.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.controllers.BaseController;
import springboot.models.Skill;
import springboot.services.SkillService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*Phần quản lý kỹ năng*/

@Controller
@RequestMapping("/admin/skill")
public class SkillController extends BaseController {

    @Autowired
    private SkillService skillService;

    /* Get list skills */
    @GetMapping
    public String skill(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        @RequestParam("search") Optional<String> searchQuery) {

        addUserEmailToModel(model);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // Nếu có query tìm kiếm, gọi phương thức tìm kiếm
        Page<Skill> skillPage;
        if (searchQuery.isPresent() && !searchQuery.get().isEmpty()) {
            skillPage = skillService.searchSkills(searchQuery.get(), currentPage - 1, pageSize, "id", "asc");
        } else {
            skillPage = skillService.getAllSkills(currentPage - 1, pageSize, "id", "asc");
        }

        model.addAttribute("skillPage", skillPage);

        int totalPages = skillPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("searchQuery", searchQuery.orElse(""));  // Lưu lại giá trị tìm kiếm vào model

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

package springboot.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkillController {

    /* Skill management */
    @RequestMapping("/admin/skill")
    public String skill(){
        return "admin/pages/skill";
    }



}

package springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin(){
        return "admin/index";
    }

    /* Job management */
    @RequestMapping("/admin/job")
    public String job(){
        return "admin/pages/job";
    }

    /* Company management */
    @RequestMapping("/admin/company")
    public String company(){
        return "admin/pages/company";
    }

    /* Candidate management */
    @RequestMapping("/admin/candidate")
    public String candidate(){
        return "admin/pages/candidate";
    }

}

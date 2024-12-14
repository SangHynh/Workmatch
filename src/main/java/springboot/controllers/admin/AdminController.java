package springboot.controllers.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.controllers.BaseController;

@Controller
public class AdminController extends BaseController {

    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    /* Job management */
    @RequestMapping("/admin/job")
    public String job() {
        return "admin/pages/job";
    }

    /* Company management */
    @RequestMapping("/admin/company")
    public String company() {
        return "admin/pages/company";
    }

    /* Candidate management */
    @RequestMapping("/admin/candidate")
    public String candidate() {
        return "admin/pages/candidate";
    }


}

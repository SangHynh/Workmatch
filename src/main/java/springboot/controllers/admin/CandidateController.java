package springboot.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.controllers.BaseController;

/* Phần quản lý ứng viên*/
@Controller("adminCandidateController")
@RequestMapping("/admin/candidate")
public class CandidateController extends BaseController {


    /* Candidate management */
    @GetMapping
    public String candidate(Model model)
    {
        addUserEmailToModel(model);
        return "admin/pages/candidate";
    }



}

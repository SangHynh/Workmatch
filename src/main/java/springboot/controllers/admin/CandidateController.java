package springboot.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.controllers.BaseController;

/* Phần quản lý ứng viên*/
public class CandidateController extends BaseController {

    /* Candidate management */
    @RequestMapping("/admin/candidate")
    public String candidate(Model model)
    {
        addUserEmailToModel(model);
        return "admin/pages/candidate";
    }



}

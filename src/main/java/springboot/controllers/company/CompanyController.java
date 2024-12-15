package springboot.controllers.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.controllers.BaseController;

@Controller("companyCompanyController")
@RequestMapping("/company")
public class CompanyController extends BaseController {


    @GetMapping("")
    public String company(Model model) {
        addUserAndCompanyToModel(model);
        addUserEmailToModel(model);
        return "company/index";
    }

}

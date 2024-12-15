package springboot.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.controllers.BaseController;
import springboot.models.Candidate;
import springboot.models.City;
import springboot.services.CandidateService;
import springboot.services.CityService;
import springboot.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller("companyCompanyController")
@RequestMapping("/company")
public class CompanyController extends BaseController {


    @Autowired
    private CityService cityService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String company(Model model) {
        addUserAndCompanyToModel(model);
        addUserEmailToModel(model);
        return "company/index";
    }


}

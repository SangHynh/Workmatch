package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.models.Company;
import springboot.services.CityService;
import springboot.services.CompanyService;
import springboot.services.SkillService;

import java.util.List;

@Controller
@SpringBootApplication
public class WorkMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMatchApplication.class, args);
    }

    @Autowired
    private CityService cityService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("cities", cityService.getAllCities());

        List<Company> randomCompanies = companyService.getRandomCompanies();
        model.addAttribute("randomCompanies", randomCompanies);

        return "index";
    }

}

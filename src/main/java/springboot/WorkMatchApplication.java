package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.models.Company;
import springboot.models.Job;
import springboot.services.CityService;
import springboot.services.CompanyService;
import springboot.services.JobService;
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

    @Autowired
    private JobService jobService;  // Tiêm JobService vào controller

    @RequestMapping("/")
    public String index(Model model) {
        // Các dữ liệu bạn đã có
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("cities", cityService.getAllCities());

        List<Company> randomCompanies = companyService.getRandomCompanies();
        model.addAttribute("randomCompanies", randomCompanies);

        // Lấy danh sách công việc gợi ý ngẫu nhiên
        List<Job> jobSuggestions = jobService.getJobSuggestions();
        model.addAttribute("jobSuggestions", jobSuggestions);  // Thêm vào model

        return "index";  // Trả về view (index.jsp hoặc template)
    }
}

package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.services.CityService;

@Controller
@SpringBootApplication
public class WorkMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMatchApplication.class, args);
    }

    @Autowired
    private CityService cityService;  // City service để gọi phương thức getAllCities()


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "index";  // Trả về view 'index'
    }

}

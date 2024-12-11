package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class WorkMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMatchApplication.class, args);
    }


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/public")
    public String admin() {
        return "index";
    }

}

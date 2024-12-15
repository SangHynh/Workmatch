package springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mainCompanyController")
@RequestMapping("/company")
public class CompanyController {

    @GetMapping
    public String company() {
        return "company/index";
    }


}

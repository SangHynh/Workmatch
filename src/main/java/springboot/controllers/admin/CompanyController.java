package springboot.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.controllers.BaseController;
import springboot.models.City;
import springboot.models.Company;
import springboot.services.CityService;
import springboot.services.CompanyService;
import springboot.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* Quản lý công ty trong CMS */
@Controller("adminCompanyController")
@RequestMapping("/admin/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    /* Lấy danh sách công ty */
    @GetMapping
    public String company(Model model, @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          @RequestParam("search") Optional<String> searchQuery,
                          @RequestParam("city") Optional<Long> city) {

        addUserEmailToModel(model);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // Lấy danh sách thành phố để hiển thị trong combobox
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        // Xử lý phân trang và lọc theo thành phố và tìm kiếm
        Page<Company> companyPage;
        if (searchQuery.isPresent() && !searchQuery.get().isEmpty() && city.isPresent()) {
            companyPage = companyService.searchCompaniesByCity(searchQuery.get(), city.get(), currentPage - 1, pageSize, "id", "asc");
        } else if (searchQuery.isPresent() && !searchQuery.get().isEmpty()) {
            companyPage = companyService.searchCompanies(searchQuery.get(), currentPage - 1, pageSize, "id", "asc");
        } else if (city.isPresent()) {
            companyPage = companyService.getCompaniesByCity(city.get(), currentPage - 1, pageSize, "id", "asc");
        } else {
            companyPage = companyService.getAllCompanies(currentPage - 1, pageSize, "id", "asc");
        }

        // Lấy email cho từng công ty
        for (Company company : companyPage) {
            String email = userService.getEmailByCompanyId(company.getId()); // Lấy email của công ty
            company.setEmail(email);  // Gán email vào company
        }

        model.addAttribute("companyPage", companyPage);

        int totalPages = companyPage.getTotalPages();
        List<Integer> pageNumbers = totalPages > 0
                ? IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList())
                : List.of();
        model.addAttribute("pageNumbers", pageNumbers);

        model.addAttribute("searchQuery", searchQuery.orElse(""));
        model.addAttribute("selectedCity", city.orElse(null));

        return "admin/pages/company";
    }

}
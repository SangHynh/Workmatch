package springboot.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.controllers.BaseController;
import springboot.models.Company;
import springboot.services.CompanyService;
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

    /* Lấy danh sách công ty */
    @GetMapping
    public String company(Model model, @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          @RequestParam("search") Optional<String> searchQuery) {

        addUserEmailToModel(model);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // Xử lý phân trang
        Page<Company> companyPage;
        if (searchQuery.isPresent() && !searchQuery.get().isEmpty()) {
            companyPage = companyService.searchCompanies(searchQuery.get(), currentPage - 1, pageSize, "id", "asc");
        } else {
            companyPage = companyService.getAllCompanies(currentPage - 1, pageSize, "id", "asc");
        }

        model.addAttribute("companyPage", companyPage);

        int totalPages = companyPage.getTotalPages();
        List<Integer> pageNumbers = totalPages > 0
                ? IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList())
                : List.of();
        model.addAttribute("pageNumbers", pageNumbers);

        model.addAttribute("searchQuery", searchQuery.orElse(""));

        return "admin/pages/company";
    }
}

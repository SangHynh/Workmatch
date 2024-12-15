package springboot.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

@Controller
public class CandidateController extends BaseController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    /* Candidate management */
    @GetMapping("/company/candidate")
    public String candidate(Model model, @RequestParam("page") Optional<Integer> page,
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
        Page<Candidate> candidatePage;
        if (searchQuery.isPresent() && !searchQuery.get().isEmpty() && city.isPresent()) {
            candidatePage = candidateService.searchCandidatesByCity(searchQuery.get(), city.get(), currentPage - 1, pageSize, "id", "asc");
        } else if (searchQuery.isPresent() && !searchQuery.get().isEmpty()) {
            candidatePage = candidateService.searchCandidates(searchQuery.get(), currentPage - 1, pageSize, "id", "asc");
        } else if (city.isPresent()) {
            candidatePage = candidateService.getCandidatesByCity(city.get(), currentPage - 1, pageSize, "id", "asc");
        } else {
            candidatePage = candidateService.getAllCandidates(currentPage - 1, pageSize, "id", "asc");
        }

        // Lấy email cho từng công ty
        for (Candidate candidate : candidatePage) {
            String email = userService.getEmailByCandidateId(candidate.getId());
            candidate.setEmail(email);  // Gán email vào company
        }

        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        List<Integer> pageNumbers = totalPages > 0
                ? IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList())
                : List.of();
        model.addAttribute("pageNumbers", pageNumbers);

        model.addAttribute("searchQuery", searchQuery.orElse(""));
        model.addAttribute("selectedCity", city.orElse(null));

        return "company/pages/candidate";
    }

}

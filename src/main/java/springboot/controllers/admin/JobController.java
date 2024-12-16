package springboot.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.controllers.BaseController;
import springboot.models.Job;
import springboot.repositories.CompanyRepository;
import springboot.services.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller("adminJobController")
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyRepository companyRepository;

    /* Get list skills */
    @GetMapping("/admin/job")
    public String job(Model model, @RequestParam("page") Optional<Integer> page,
                      @RequestParam("size") Optional<Integer> size,
                      @RequestParam("jobName") Optional<String> jobNameQuery,
                      @RequestParam("companyName") Optional<String> companyNameQuery) {

        addUserEmailToModel(model);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // Khởi tạo jobPage với tìm kiếm theo jobName và companyName
        Page<Job> jobPage;
        String jobName = jobNameQuery.orElse("");
        String companyName = companyNameQuery.orElse("");

        // Tìm kiếm các công việc theo jobName và companyName
        jobPage = jobService.getAllJobsByJobNameAndCompanyName(jobName, companyName, currentPage - 1, pageSize, "id", "asc");

        // Thêm dữ liệu vào model
        model.addAttribute("jobPage", jobPage);

        // Phân trang
        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        // Thêm các query tìm kiếm vào model
        model.addAttribute("jobNameQuery", jobName);
        model.addAttribute("companyNameQuery", companyName);

        return "admin/pages/job";
    }



}

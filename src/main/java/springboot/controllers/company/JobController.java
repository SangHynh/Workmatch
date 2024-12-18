package springboot.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.controllers.BaseController;
import springboot.models.Company;
import springboot.models.Job;
import springboot.services.JobService;
import springboot.repositories.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller("companyJobController")
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyRepository companyRepository;

    /* Get list skills */
    @GetMapping("/company/job")
    public String skill(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        @RequestParam("search") Optional<String> searchQuery) {

        addUserEmailToModel(model);

        // Lấy ID công ty của người dùng đang đăng nhập
        Long companyId = getCompanyIdFromUser(); // Cần xác định cách lấy companyId từ session hoặc context

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // Nếu có query tìm kiếm, gọi phương thức tìm kiếm
        Page<Job> jobPage;
        if (searchQuery.isPresent() && !searchQuery.get().isEmpty()) {
            jobPage = jobService.searchJobs(searchQuery.get(), currentPage - 1, pageSize, "id", "asc");
        } else {
            jobPage = jobService.getJobsByCompanyId(companyId, currentPage - 1, pageSize, "id", "asc");
        }

        model.addAttribute("jobPage", jobPage);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("searchQuery", searchQuery.orElse(""));

        return "company/pages/job";
    }


    // Hàm xử lý form thêm công việc
    @PostMapping("/company/job/add")
    public String addJob(@RequestParam("jobName") String jobName,
                         @RequestParam("jobDesc") String jobDesc,
                         Model model) {
        // Lấy ID công ty của người dùng đang đăng nhập
        Long companyId = getCompanyIdFromUser(); // Cần xác định cách lấy companyId từ session hoặc context

        // Tạo đối tượng Job mới và lưu vào cơ sở dữ liệu
        Job newJob = new Job();
        newJob.setJobName(jobName);
        newJob.setJobDesc(jobDesc);

        // Lấy công ty từ repository dựa vào companyId
        companyRepository.findById(companyId).ifPresent(company -> newJob.setCompany(company));

        // Lưu công việc mới vào cơ sở dữ liệu
        jobService.saveJob(newJob);

        // Chuyển hướng về trang danh sách công việc sau khi thêm thành công
        return "redirect:/company/job";
    }


    @PostMapping("/company/job/update")
    public String updateJob(@ModelAttribute("job") Job job, @RequestParam("companyId") Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        job.setCompany(company);  // Gán companyId vào job
        jobService.updateJob(job);     // Gọi service để cập nhật job
        return "redirect:/company/job";  // Điều hướng đến trang danh sách công việc
    }



    @GetMapping("/company/job/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        jobService.deleteJob(id);
        return "redirect:/company/job";  // Sau khi xóa, quay lại trang danh sách công việc
    }




}

package springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import springboot.dtos.CompanyRegisterDTO;
import springboot.enums.Role;
import springboot.dtos.UserRegisterDTO;
import springboot.models.City;
import springboot.models.Company;
import springboot.models.User;
import springboot.repositories.CityRepository;
import springboot.services.CompanyService;
import springboot.services.UserService;

import java.util.List;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "/public/login";
    }

    @GetMapping("/register/{role}")
    public String showRegisterPage(@PathVariable String role, Model model) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        model.addAttribute("user", userRegisterDTO);
        return "/public/" +role + "-register";
    }

    @GetMapping("/role-selection")
    public String showRegisterPage() {
        return "/public/role-selection";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password.");
            return "/public/login";
        }

        model.addAttribute("user", user);

        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin";
        } else if (user.getRole() == Role.COMPANY) {
            return "redirect:/company";
        }
        else if (user.getRole() == Role.CANDIDATE) {
            return "redirect:/candidate";
        }

        return "/public/login";
    }


    @PostMapping("/register/admin")
    public String handleRegister(@ModelAttribute UserRegisterDTO userRegisterDTO, Model model) {
        // Kiểm tra nếu mật khẩu xác nhận khớp với mật khẩu
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "register"; // Quay lại trang đăng ký nếu mật khẩu không khớp
        }

        // Tiến hành lưu thông tin người dùng sau khi xác minh mật khẩu
        User newUser = new User();
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setPassword(userRegisterDTO.getPassword());
        newUser.setRole(Role.ADMIN);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        userService.registerUser(newUser);

        model.addAttribute("successMessage", "Registration successful! Please log in.");

        return "/public/login";
    }

    @PostMapping("/register/company")
    public String handleCompanyRegister(@ModelAttribute CompanyRegisterDTO companyRegisterDTO, Model model) {
        // Kiểm tra nếu mật khẩu xác nhận khớp với mật khẩu
        if (!companyRegisterDTO.getPassword().equals(companyRegisterDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "/public/register/company-register";
        }

        List<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);

        // Tạo đối tượng User cho công ty
        User newUser = new User();
        newUser.setEmail(companyRegisterDTO.getEmail());
        newUser.setPassword(companyRegisterDTO.getPassword());
        newUser.setRole(Role.COMPANY);

        try {
            // Lưu thông tin người dùng vào cơ sở dữ liệu
            userService.registerUser(newUser);

            // Tạo đối tượng Company
            Company newCompany = new Company();
            newCompany.setCompName(companyRegisterDTO.getCompName());
            newCompany.setPhone(companyRegisterDTO.getPhone());
            newCompany.setAbout(companyRegisterDTO.getAbout());
            newCompany.setWebUrl(companyRegisterDTO.getWebUrl());
            newCompany.setAddress(companyRegisterDTO.getAddress());
            newCompany.setCity(companyRegisterDTO.getCity());

            // Lưu thông tin công ty vào cơ sở dữ liệu
            companyService.registerCompany(newCompany);

            // Liên kết công ty với người dùng
            newUser.setCompany(newCompany);
            userService.updateCompanyAccount(newUser);

            model.addAttribute("successMessage", "Company registration successful! Please log in.");
            return "/public/login";

        } catch (Exception e) {
            // Nếu có lỗi trong quá trình tạo công ty, xóa tài khoản người dùng đã tạo
            System.out.println(e.getMessage());
            userService.deleteUser(newUser);
            model.addAttribute("error", "An error occurred while registering the company.");
            return "/public/company-register";
        }
    }



}

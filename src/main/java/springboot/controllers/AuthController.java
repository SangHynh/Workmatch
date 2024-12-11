package springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import springboot.enums.Role;
import springboot.models.RegisterDTO;
import springboot.models.User;
import springboot.services.UserService;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "/public/login";
    }

    @GetMapping("/register/{role}")
    public String showRegisterPage(@PathVariable String role, Model model) {
        RegisterDTO registerDTO = new RegisterDTO();  // Hoặc sử dụng đối tượng DTO đúng
        model.addAttribute("user", registerDTO);
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
    public String handleRegister(@ModelAttribute RegisterDTO registerDTO, Model model) {
        // Kiểm tra nếu mật khẩu xác nhận khớp với mật khẩu
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "register"; // Quay lại trang đăng ký nếu mật khẩu không khớp
        }

        // Tiến hành lưu thông tin người dùng sau khi xác minh mật khẩu
        User newUser = new User();
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(registerDTO.getPassword());
        newUser.setRole(Role.ADMIN);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        userService.registerUser(newUser);

        model.addAttribute("successMessage", "Registration successful! Please log in.");

        return "/public/login";
    }

}

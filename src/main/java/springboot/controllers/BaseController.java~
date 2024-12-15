package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import springboot.models.User;
import springboot.services.UserService;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    // This can be a method used to add the user email to the model for all pages
    protected void addUserEmailToModel(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        model.addAttribute("userEmail", email);
    }

    // Phương thức này sẽ lấy thông tin đầy đủ người dùng và công ty của họ nếu có
    protected void addUserAndCompanyToModel(Model model) {
        // Lấy đối tượng UserDetails hiện tại từ SecurityContext
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        // Tìm người dùng theo email
        User user = userService.findByEmail(email);

        // Nếu người dùng có vai trò là công ty, lấy thông tin công ty
        if (user != null && user.isCompany()) {
            model.addAttribute("user", user); // Thêm người dùng vào model
            model.addAttribute("company", user.getCompany()); // Thêm công ty của người dùng vào model
        } else {
            model.addAttribute("user", user); // Thêm người dùng vào model nếu không phải công ty
        }
    }
    
}
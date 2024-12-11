package springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import springboot.services.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/public/**")
                        .disable()
                )
                .authorizeRequests(auth -> auth
                        .requestMatchers("/app/**").permitAll()  // Các trang công khai
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")  // Chỉ cho phép ADMIN
                        .requestMatchers("/candidate/**").hasAuthority("CANDIDATE")  // Chỉ cho phép CANDIDATE
                        .requestMatchers("/company/**").hasAuthority("COMPANY")  // Chỉ cho phép COMPANY
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customSuccessHandler())
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/public/access-denied")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                )
                .httpBasic();
        return http.build();
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // Custom handler để điều hướng người dùng theo vai trò
    private AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst()
                    .orElse("");

            String redirectUrl;
            switch (role) {
                case "ADMIN":
                    redirectUrl = "/admin";
                    break;
                case "CANDIDATE":
                    redirectUrl = "/candidate";
                    break;
                case "COMPANY":
                    redirectUrl = "/company";
                    break;
                default:
                    redirectUrl = "/home"; // Mặc định nếu không có role phù hợp
                    break;
            }

            response.sendRedirect(redirectUrl); // Chuyển hướng đến URL tương ứng
        };
    }
}

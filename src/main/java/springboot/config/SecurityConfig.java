package springboot.config;

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
                        .requestMatchers("/app/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/candidate/**").hasAuthority("CANDIDATE")
                        .requestMatchers("/company/**").hasAuthority("COMPANY")
                )
                // Cấu hình form login cho từng role
                .formLogin(form -> form
                        .loginPage("/login") // Trang login mặc định nếu chưa đăng nhập
                        .permitAll()
                        .loginProcessingUrl("/login")  // Xử lý login tại URL này
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/access-denied")
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

    // Định nghĩa handler để điều hướng người dùng theo role
    private AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst()
                    .orElse("");

            String redirectUrl;
            switch (role) {
                case "ADMIN":
                    redirectUrl = "/admin/home";
                    break;
                case "CANDIDATE":
                    redirectUrl = "/candidate/home";
                    break;
                case "COMPANY":
                    redirectUrl = "/company/home";
                    break;
                default:
                    redirectUrl = "/home";
                    break;
            }

            response.sendRedirect(redirectUrl);
        };
    }
}

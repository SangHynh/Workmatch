package springboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.models.City;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private LocalDate dob;
    private String phone;
    private String address;
    private City city;
}

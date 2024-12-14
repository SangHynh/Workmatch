package springboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String compName;
    private String phone;
    private String about;
    private String webUrl;
//    private Long addressId;
}

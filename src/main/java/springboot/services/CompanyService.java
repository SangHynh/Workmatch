package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.models.Company;
import springboot.models.User;
import springboot.repositories.CompanyRepository;
import springboot.repositories.UserRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    
    private final UserRepository userRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }


    public Company registerCompany(Company company) {
        return companyRepository.save(company);
    }





}
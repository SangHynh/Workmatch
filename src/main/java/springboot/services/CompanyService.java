package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.models.Company;
import springboot.models.User;
import springboot.repositories.CompanyRepository;
import springboot.repositories.UserRepository;

import java.util.List;

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

    // Lấy tất cả các công ty với phân trang và sắp xếp
    public Page<Company> getAllCompanies(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyRepository.findAll(pageable);
    }

    // Tìm kiếm các công ty theo từ khóa với phân trang và sắp xếp
    public Page<Company> searchCompanies(String keyword, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyRepository.findByCompNameContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword, pageable);
    }

    // Lọc công ty theo thành phố
    public Page<Company> getCompaniesByCity(Long cityId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyRepository.findByCityId(cityId, pageable);
    }

    // Tìm kiếm công ty theo tên và thành phố
    public Page<Company> searchCompaniesByCity(String keyword, Long cityId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyRepository.findByCompNameContainingIgnoreCaseAndCityId(keyword, cityId, pageable);
    }

    // Lấy danh sách ngẫu nhiên 6 công ty
    public List<Company> getRandomCompanies() {
        List<Company> companies = companyRepository.findRandomCompanies(6);
        return companies.isEmpty() ? companies : companies.subList(0, Math.min(companies.size(), 6));
    }







}
package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.models.Candidate;
import springboot.repositories.CandidateRepository;
import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

//    public Page<Candidate> getCandidates(int page, int size, String sortBy, String direction) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
//        return candidateRepository.findAll(pageable);
//    }
//
//
//
//    public Optional<Candidate> getCandidate(Long id) {
//        return candidateRepository.findById(id);
//    }
//
//    public Candidate updateCandidate(Candidate candidate) {
//        return candidateRepository.save(candidate);
//    }


    public Candidate registerCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // Lấy tất cả các ứng viên với phân trang và sắp xếp
    public Page<Candidate> getAllCandidates(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return candidateRepository.findAll(pageable);
    }

    // Tìm kiếm các ứng viên theo từ khóa với phân trang và sắp xếp
    public Page<Candidate> searchCandidates(String keyword, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return candidateRepository.findByFullNameContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword, pageable);
    }

    // Lọc các ứng viên theo thành phố
    public Page<Candidate> getCandidatesByCity(Long cityId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return candidateRepository.findByCityId(cityId, pageable);
    }

    // Tìm kiếm ứng viên theo tên và thành phố
    public Page<Candidate> searchCandidatesByCity(String keyword, Long cityId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return candidateRepository.findByFullNameContainingIgnoreCaseAndCityId(keyword, cityId, pageable);
    }


}
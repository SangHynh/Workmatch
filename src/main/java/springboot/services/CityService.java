package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.models.City;
import springboot.repositories.CityRepository;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // Lấy tất cả các thành phố
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}

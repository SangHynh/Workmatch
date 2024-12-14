package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAll();
}

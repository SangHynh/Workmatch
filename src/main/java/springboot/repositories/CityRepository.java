package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}

package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
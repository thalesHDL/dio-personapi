package one.digital.inovation.diopersonapi.repository;

import one.digital.inovation.diopersonapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

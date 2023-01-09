package pl.markowski.kinoteatr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.kinoteatr.model.Concession;

@Repository
public interface ConcessionRepository extends JpaRepository<Concession, Long> {

}

package pl.markowski.kinoteatr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.kinoteatr.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

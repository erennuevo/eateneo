package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Stall;

@Repository
public interface StallRepository extends JpaRepository<Stall, Long> {
    Stall findByName(String name);

    List<Stall> findByCafeteria(String cafeteria);

    List<Stall> findByNameContaining(String name); 
}

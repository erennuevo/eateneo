package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Stall;

@Repository
public interface StallRepository extends JpaRepository<Stall, Long> 
{
	public Stall findByName(String name);
}
package app.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> 
{
	public List<User> findAll();
	public User findByIdNumber(String idNumber);
	List<User> findByNameContaining(String name);
}

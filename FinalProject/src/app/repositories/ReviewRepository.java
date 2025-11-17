package app.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Review;
import app.entities.Meal;
import app.entities.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	 List<Review> findByMeal(Meal meal);
	 List<Review> findByUser(User user); 
	 List<Review> findByMealIn(List<Meal> meals);
}

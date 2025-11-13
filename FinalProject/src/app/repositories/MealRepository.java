package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> 
{
	public List<Meal> findAll();
	public List<Meal> findByMealType(String mealType);
	public List<Meal> findByCostLessThanEqual(Integer cost);
	public List<Meal> findByMealTypeAndCostLessThanEqual(String mealType, Integer cost);
	public Meal findByPk(Long pk);
	public Meal findByName(String name); 
}
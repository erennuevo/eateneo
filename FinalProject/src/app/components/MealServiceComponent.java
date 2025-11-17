package app.components;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import app.entities.Meal;
import app.entities.Stall;
import app.repositories.MealRepository;
import app.repositories.StallRepository;

@Component
@DependsOn("stallServiceComponent")
public class MealServiceComponent {
	
	@Autowired
	private MealRepository mealRepo;
	
	@Autowired
	private StallRepository stallRepo;
	
	private Random random = new Random();
	
	public List<Meal> getAllMeals()
	{
		List<Meal> meals = mealRepo.findAll();
		
		if (meals.isEmpty())
		{
			return null;
		}
		
        return meals;
	}
	
	public Meal getRecommendedMeal(String mealType, Integer cost)
	{
		List<Meal> meals = mealRepo.findByMealTypeAndCostLessThanEqual(mealType, cost);
		
		if (meals.isEmpty())
		{
			return null;
		}
		
        int randomIndex = random.nextInt(meals.size());
        return meals.get(randomIndex);
	}
	
	public List<Meal> searchMeal(String name)
	{
		List<Meal> meals = mealRepo.findByNameContaining(name);
		
		if (meals.isEmpty())
		{
			return null;
		}
		
        return meals;
	}
	
	public List<Meal> getMealsByStall(String stallName)
	{
		Stall stall = stallRepo.findByName(stallName); 
		List<Meal> stallMeals = mealRepo.findByStall(stall);
		
		if (stallMeals.isEmpty())
		{
			return null;
		}
		
        return stallMeals;
	}
	
	@PostConstruct
	public void init()
	{
		if (mealRepo.count()==0)
		{
			Meal meal = makeMeal("Mongch Rice Bowl", "regular", 150, "A Mongolian-style meal with vegetables and meat.", "Mongch"); 
			mealRepo.save(meal);
			
			meal = makeMeal("Gyro Chicken Fries", "snack", 100, "Fries loaded with shawarma-style meat and vegetables.", "GHE"); 
			mealRepo.save(meal);
			
			meal = makeMeal("Matcha Wave", "drink", 170, "Classic matcha latte.", "Day Off"); 
			mealRepo.save(meal);
			
			meal = makeMeal("Dying Burger", "snack", 100, "Burger with bacon, patty, and cheese.", "Hunger Buster");
			mealRepo.save(meal);
			
			meal = makeMeal("Spanish Latte", "drink", 110, "Classic spanish latte.", "Ondo");
			mealRepo.save(meal);
			
			meal = makeMeal("Whipped Maple Matcha", "drink", 180, "Matcha with a maple syrup twist.", "Day Off"); 
			mealRepo.save(meal);
		}
	}

	private Meal makeMeal(String name, String mealType, Integer cost, String description, String stallName)
	{
		Meal meal = new Meal();
		meal.setName(name);
		
		Stall stall = stallRepo.findByName(stallName); 
		meal.setStall(stall);
		
		meal.setMealType(mealType);
		meal.setCost(cost);
		meal.setDescription(description);
		
		return meal;
	} 
}

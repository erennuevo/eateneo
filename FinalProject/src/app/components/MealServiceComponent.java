package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.entities.Meal;
import app.entities.Stall;
import app.repositories.MealRepository;
import app.repositories.StallRepository;

@Component
public class MealServiceComponent {
	
	@Autowired
	private MealRepository mealRepo;
	
	@Autowired
	private StallRepository stallRepo;
	
	@PostConstruct
	public void init()
	{
		if (mealRepo.count()==0)
		{
			Meal meal = makeMeal("Mongch Rice Bowl", "regular", 150, "A Mongolian-style meal with vegetables and meat.", "Mongch"); 
			mealRepo.save(meal);
		}
	}

	public Meal makeMeal(String name, String mealType, Integer cost, String description, String stallName)
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

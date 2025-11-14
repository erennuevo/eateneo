package app.rest.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import app.components.MealServiceComponent;
import app.entities.Meal;

@Path("/eateneo")
public class MealController {
	
	Logger logger = LoggerFactory.getLogger(MealController.class);
	
	@Autowired
	private MealServiceComponent mealComponent;

	@GET
    @Path("/getRecommendation")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Meal getRecommendation(
            @QueryParam("mealType") String mealType,
            @QueryParam("cost") Integer cost) throws Exception 
	{
        Meal reco = mealComponent.getRecommendedMeal(mealType, cost);

        return reco; 
    }
	
	@GET
    @Path("/searchFood")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Meal> searchFood(
            @QueryParam("name") String name) throws Exception
	{
        List<Meal> searchedMeals = mealComponent.searchMeal(name);

        return searchedMeals; 
    }
	
	@GET
    @Path("/getMealsByStall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Meal> getMealsByStall(
            @QueryParam("stallName") String stallName) throws Exception
	{
        List<Meal> stallMeals = mealComponent.getMealsByStall(stallName);

        return stallMeals; 
    }
}
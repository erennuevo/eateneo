package app.rest.controllers;

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

	@POST
    @Path("/recommend")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Meal sendMessage(
            @FormParam("mealType") String mealType,
            @FormParam("cost") Integer cost) throws Exception 
	{
        Meal meal = mealComponent.getRecommendedMeal(mealType, cost);

        return meal;
    }
}
package app.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import app.components.ReviewServiceComponent;
import app.entities.Review;
import app.dto.ReviewDto;

@Path("/eateneo")
public class ReviewController {

    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewServiceComponent reviewService;


    @POST
    @Path("/addReview")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Review addReview(ReviewDto reviewDto) throws Exception {
        if (reviewDto.getRating() == null || reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
            throw new WebApplicationException("{\"error\": \"Rating must be between 1 and 5\", \"status\": 400}", 400);
        }

        if (reviewDto.getComment() != null && reviewDto.getComment().length() > 100) {
            throw new WebApplicationException("{\"error\": \"Comment too long (max 100 characters)\", \"status\": 400}", 400);
        }

        return reviewService.addReview(reviewDto);
    }
    
    @GET
    @Path("/getReviewsByUser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewsByUser(@QueryParam("idNumber") String idNumber) {
        if (idNumber == null || idNumber.isEmpty()) {
            throw new WebApplicationException("{\"error\": \"Missing parameter: idNumber\", \"status\": 400}", 400);
        }

        try {
            return reviewService.getReviewsByUser(idNumber);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException("{\"error\": \"" + e.getMessage() + "\", \"status\": 404}", 404);
        }
    } 
    
    @GET
    @Path("/getAverageOfMeal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAverageOfMeal(@QueryParam("mealId") Long mealId) {
        String output = "The average rating for this meal is: " + reviewService.getAverageOfMeal(mealId);
        
        return output;
    }

    @GET
    @Path("/getAverageOfStall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAverageOfStall(@QueryParam("stallName") String stallName) {
    	String output = "The average rating for " + stallName + " is: " + reviewService.getAverageOfStall(stallName);
        
        return output;
    }
  
}

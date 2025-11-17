package app.components;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Meal;
import app.entities.Review;
import app.entities.User;
import app.repositories.ReviewRepository;
import app.repositories.UserRepository;
import app.repositories.MealRepository;
import app.dto.ReviewDto;


@Component
public class ReviewServiceComponent {

	@Autowired
	private ReviewRepository reviewRepo;
	
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MealRepository mealRepo;
    
    @Autowired
    private MealServiceComponent mealComponent;
    
    public List<Review> getAllReviewsByMeal(Long mealId) {
        Meal meal = mealRepo.findById(mealId)
            .orElseThrow(() -> new IllegalArgumentException("Meal not found with pk: " + mealId));

        List<Review> reviews = reviewRepo.findByMeal(meal);

        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("No reviews found for meal with pk: " + mealId);
        }

        return reviews;
    }

    public List<Review> getReviewsByUser(String idNumber) {
        User user = userRepo.findByIdNumber(idNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with idNumber: " + idNumber);
        }

        List<Review> reviews = reviewRepo.findByUser(user);
        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("No reviews found for user with idNumber: " + idNumber);
        }

        return reviews;
    }
    
    @Transactional
    public String addReview(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        if (review.getComment() != null && review.getComment().length() > 80) {
            throw new IllegalArgumentException("Comment must be 80 characters or less");
        }

        Long mealId = review.getMeal() != null ? review.getMeal().getPk() : null;
        if (mealId == null) {
            throw new IllegalArgumentException("Meal must be specified");
        }

        Meal meal = mealRepo.findById(mealId)
            .orElseThrow(() -> new IllegalArgumentException("Meal not found with pk: " + mealId));

        String idNumber = review.getUser() != null ? review.getUser().getIdNumber() : null;
        if (idNumber == null) {
            throw new IllegalArgumentException("User must be specified");
        }

        User user = userRepo.findByIdNumber(idNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with idNumber: " + idNumber);
        }

        review.setMeal(meal);
        review.setUser(user);

        reviewRepo.save(review);

        return "Review sent!";
    }


    @Transactional
    public Review addReview(ReviewDto reviewDto) {
        User user = userRepo.findByIdNumber(reviewDto.getUser());
        if (user == null) throw new IllegalArgumentException("User not found with idNumber: " + reviewDto.getUser());

        Long mealId = reviewDto.getMeal().getPk();
        Meal meal = mealRepo.findById(mealId)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with pk: " + mealId));

        if (reviewDto.getRating() < 1 || reviewDto.getRating() > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        if (reviewDto.getComment() != null && reviewDto.getComment().length() > 80)
            throw new IllegalArgumentException("Comment must be 80 characters or less");

        Review review = new Review();
        review.setUser(user);
        review.setMeal(meal);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        return reviewRepo.save(review); 
    }

    public Double getAverageOfMeal(Long mealId) {
    	
    	List<Review> reviews = getAllReviewsByMeal(mealId);
    	
    	int sum = 0;
    	for (Review r : reviews) {
    	    sum += r.getRating();
    	}

    	double average = (double) sum / reviews.size();
    	average = Math.round(average * 100.0) / 100.0;
    	
    	return average;
    }
    
    public Double getAverageOfStall(String stallName) {

    	List<Meal> meals = mealComponent.getMealsByStall(stallName);
        List<Review> reviews = reviewRepo.findByMealIn(meals);

        int sum = 0;
        for (Review r : reviews) {
            sum += r.getRating();
        }

        double average = (double) sum / reviews.size();
        average = Math.round(average * 100.0) / 100.0;

        return average;
    }

    
    

}

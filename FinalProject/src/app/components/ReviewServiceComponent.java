package app.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Meal;
import app.entities.Review;
import app.entities.User;
import app.repositories.ReviewRepository;
import app.repositories.UserRepository;
import app.repositories.MealRepository;

@Component
public class ReviewServiceComponent {

	@Autowired
	private ReviewRepository reviewRepo;
	
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MealRepository mealRepo;
    
    public List<Review> getAllReviewsByMeal(Long mealPk) {
        Meal meal = mealRepo.findById(mealPk)
            .orElseThrow(() -> new IllegalArgumentException("Meal not found with pk: " + mealPk));

        List<Review> reviews = reviewRepo.findByMeal(meal);

        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("No reviews found for meal with pk: " + mealPk);
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
    
    public String addReview(Review review) {
        // Validate rating
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        // Validate comment length
        if (review.getComment() != null && review.getComment().length() >= 80) {
            throw new IllegalArgumentException("Comment must be 80 characters or less");
        }

        // Check if Meal exists
        Long mealPk = review.getMeal() != null ? review.getMeal().getPk() : null;
        if (mealPk == null) {
            throw new IllegalArgumentException("Meal must be specified");
        }

        Meal meal = mealRepo.findById(mealPk)
            .orElseThrow(() -> new IllegalArgumentException("Meal not found with pk: " + mealPk));

        // Check if User exists
        String idNumber = review.getUser() != null ? review.getUser().getIdNumber() : null;
        if (idNumber == null) {
            throw new IllegalArgumentException("User must be specified");
        }

        User user = userRepo.findByIdNumber(idNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with idNumber: " + idNumber);
        }

        // Set validated Meal and User to the review (optional, ensures correct references)
        review.setMeal(meal);
        review.setUser(user);

        // Save review
        reviewRepo.save(review);

        return "Review sent!";
    }




}

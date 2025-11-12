package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	private Long pk;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
	private Meal meal;
	
	@Range(min = 1, max=5)
	@Column(nullable = false)
	private Integer rating;
	
	@Column
	@Size(max = 80)
	private String comment;
	
	public Review() {}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Review(Long pk, User user, Meal meal, @Range(min = 1, max = 5) Integer rating,
			@Size(max = 80) String comment) {
		super();
		this.pk = pk;
		this.user = user;
		this.meal = meal;
		this.rating = rating;
		this.comment = comment;
	}
	
}

package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	private Long pk;
	
	@Column
	@NotNull(message="Name must not be null.")
	private String name;
	
	@ManyToOne
	private Stall stall;
	
	@Column
	@NotNull(message="Meal type must not be null.")
	@Pattern(regexp="(?i)(\\W|^)(regular|snack|dessert|drink)(\\W|$)", message="Type must be regular, snack, dessert, or drink.") 
	private String mealType;
	
	@Column
	@NotNull(message="Cost must not be null.")
	private Integer cost;
	
	@Column
	@Size(max = 80)
	private String description;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stall getStall() {
		return stall;
	}

	public void setStall(Stall stall) {
		this.stall = stall;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Meal [pk=" + pk + ", name=" + name + ", mealType=" + mealType + ", cost=" + cost + ", description="
				+ description + "]";
	} 
}

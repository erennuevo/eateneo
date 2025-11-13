package app.dto;

public class ReviewDto {
    private String user; 
    private MealDto meal;
    private Integer rating;
    private String comment;

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public MealDto getMeal() { return meal; }
    public void setMeal(MealDto meal) { this.meal = meal; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public static class MealDto {
        private Long pk;
        public Long getPk() { return pk; }
        public void setPk(Long pk) { this.pk = pk; }
    }
}

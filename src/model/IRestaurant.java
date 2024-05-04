package model;

/**
 * IRestaurant is an interface
 */
public interface IRestaurant {
    public String getName();
    public String getCity();
    public String getArea();
    public String getCuisine();
    public double getRating();
    public double getCost();
    public FoodType getFoodType();
    public int getOpeningHours();
    public int getClosingHours();
    public int getTimeSlot();
}

package model;

import java.util.Collection;

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
    public double getOpeningHours();
    public double getClosingHours();
    public Collection<Double> getTimeSlot();
    public int getCapacity();
}

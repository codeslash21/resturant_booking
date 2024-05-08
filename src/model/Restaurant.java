package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is to create restaurant object. It implements IRestaurant
 * @see model.IRestaurant
 */
public class Restaurant implements IRestaurant{
    protected String name;
    protected String city;
    protected String area;
    protected String cuisine;
    protected double rating;
    protected double cost;
    protected String foodType;
    protected Map<Double, Integer> timeSlotToCapacityMap = new HashMap<>();
    protected int capacity;
    public Restaurant(String name, String city, String area, String cuisine, double cost, String foodType, int capacity) {
        this.name=name;
        this.city=city;
        this.area=area;
        this.cuisine=cuisine;
        this.cost=cost;
        this.foodType=foodType;
        this.capacity=capacity;
        this.rating=4+Math.random();
    }

    /**
     * This method return the restaurant name.
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method return the city name associated with the restaurant.
     * @return city
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * This method return the area name associated with the restaurant.
     * @return area
     */
    @Override
    public String getArea() {
        return area;
    }

    /**
     * This method return the cuisine associated with the restaurant.
     * @return cuisine
     */
    @Override
    public String getCuisine() {
        return cuisine;
    }

    /**
     * This method return the restaurant rating.
     * @return rating
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * This method return the cost for two person at the restaurant.
     * @return cost
     */
    @Override
    public double getCost() {
        return cost;
    }

    /**
     * This method return the food type at the restaurant.
     * @return foodType
     */
    @Override
    public String getFoodType() {
        return foodType;
    }

    /**
     * This method will return the capacity of the restaurant at a particular time slot
     * @return capacity
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Restaurant: "+name+"\nCity: "+city+"\nArea: "+area+"\nCuisine: "+cuisine+"\nRating: "+rating+"\nCost for two: "+cost+"\nFood Type: "+foodType;
    }
}

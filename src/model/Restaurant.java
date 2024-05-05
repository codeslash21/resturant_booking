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
    protected FoodType foodType;
    protected double openingHours;
    protected double closingHours;
    protected int slotDuration;
    protected Map<Double, Integer> timeSlotToCapacityMap = new HashMap<>();
    protected int capacity;
    public Restaurant(String name, String city, String area, String cuisine, double cost, FoodType foodType, double openingHours, double closingHours, int slotDuration, int capacity) {
        this.name=name;
        this.city=city;
        this.area=area;
        this.cuisine=cuisine;
        this.cost=cost;
        this.foodType=foodType;
        this.openingHours=openingHours;
        this.closingHours=closingHours;
        this.slotDuration=slotDuration;
        this.capacity=capacity;
        for(double i=openingHours;i<=closingHours;i+=1) {
            this.timeSlotToCapacityMap.put(i, capacity);
        }
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
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * This method return the opening hours of the restaurant.
     * @return openingHours
     */
    @Override
    public double getOpeningHours() {
        return openingHours;
    }

    /**
     * This method return the closing hours of the restaurant.
     * @return closingHours
     */
    @Override
    public double getClosingHours() {
        return closingHours;
    }

    /**
     * This method return the time slot of the restaurant.
     * @return timeSlot
     */
    @Override
    public Collection<Double> getTimeSlot() {
        return timeSlotToCapacityMap.keySet();
    }

    /**
     * This method will return the capacity of the restaurant at a particular time slot
     * @return capacity
     */
    @Override
    public int getCapacity() {
        return capacity;
    }
}

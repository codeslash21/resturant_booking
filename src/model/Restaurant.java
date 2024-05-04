package model;

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
    protected int openingHours;
    protected int closingHours;
    protected int timeSlot;
    public Restaurant(String name, String city, String area, String cuisine, double rating, double cost, FoodType foodType, int openingHours, int closingHours, int timeSlot) {
        this.name=name;
        this.city=city;
        this.area=area;
        this.cuisine=cuisine;
        this.rating=rating;
        this.cost=cost;
        this.foodType=foodType;
        this.openingHours=openingHours;
        this.closingHours=closingHours;
        this.timeSlot=timeSlot;
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
    public int getOpeningHours() {
        return openingHours;
    }

    /**
     * This method return the closing hours of the restaurant.
     * @return closingHours
     */
    @Override
    public int getClosingHours() {
        return closingHours;
    }

    /**
     * This method return the time slot of the restaurant.
     * @return timeSlot
     */
    @Override
    public int getTimeSlot() {
        return timeSlot;
    }
}

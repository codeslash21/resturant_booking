package model;

import java.util.Date;

/**
 *This class is to create booking object
 */
public class Booking {
    private Customer customer;
    private IRestaurant restaurant;
    private int entryTime;
    public Booking(Customer customer, IRestaurant restaurant, int entryTime) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.entryTime = entryTime;
    }

    /**
     * This method will return the restaurant that is booked.
     * @return Restaurant object
     */
    public IRestaurant getRestaurant() {
        return this.restaurant;
    }

    @Override
    public String toString() {
        return "Restaurant: "+restaurant.getName()+" was booked at "+entryTime+" by "+customer.toString();
    }
}

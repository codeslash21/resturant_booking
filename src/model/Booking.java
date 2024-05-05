package model;

import java.util.Date;
import java.util.List;

/**
 *This class is to create booking object
 */
public class Booking {
    private Customer customer;
    private IRestaurant restaurant;
    private List<String> slot;
    public Booking(Customer customer, IRestaurant restaurant, List<String> slot) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.slot = slot;
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
        return "Restaurant: "+restaurant.getName()+" was booked at "+slot.get(0)+" by "+customer.toString();
    }
}

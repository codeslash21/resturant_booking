package model;

import java.util.List;

/**
 *This class is to create booking object
 */
public class Booking {
    private Customer customer;
    private IRestaurant restaurant;
    private String date;
    private List<String> slot;
    public Booking(Customer customer, IRestaurant restaurant, String date, List<String> slot) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.date = date;
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
        return "Restaurant: "+restaurant.getName()+"\nSlot: "+slot.get(0)+"\nDate:  "+date;
    }
}

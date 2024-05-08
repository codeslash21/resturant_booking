package api;

import model.Booking;
import model.Customer;
import model.IRestaurant;
import service.RestaurantService;
import service.UserService;

import java.util.List;
import java.util.Map;

public class CustomerResource {

    /**
     * This method creates a new Customer object
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return Customer object
     */
    public static Customer createCustomer(String firstName, String lastName, String email, String password) {
        return UserService.addCustomer(firstName, lastName, email, password);
    }

    /**
     * This method returns Customer associated with the given email and password
     * @param email
     * @param password
     * @return Customer object
     */
    public static Customer getCustomer(String email, String password) {
        return UserService.getCustomer(email, password);
    }
    /**
     * This method returns list of restaurant that matches the search criteria
     * @param searchCriteria
     * @return list of restaurant
     */
    public static List<IRestaurant> searchRestaurant(Map<String, String> searchCriteria) {
        return RestaurantService.searchRestaurant(searchCriteria);
    }

    /**
     * This method returns all the available time slots at a restaurant on a specific date for n number of people
     * @param restaurant
     * @param date
     * @param seat
     * @return list of time slots
     */
    public static List<List<String>> getTimeSlots(IRestaurant restaurant, String date, int seat) {
        return RestaurantService.getTimeSlots(restaurant, date, seat);
    }

    /**
     * This method books table for a customer at a restaurant for n number of people at given slot
     * @param customer
     * @param restaurant
     * @param slot
     * @param people
     * */
    public static void bookTable(Customer customer, IRestaurant restaurant, List<String> slot, String date, int people) {
        RestaurantService.bookTable(customer, restaurant, slot, date, people);
    }

    /**
     * This method returns all the bookings of a customer
     * @param customer
     * @return list of Booking
     * @see Booking
     */
    public static List<Booking> getAllBooking(Customer customer) {
        return RestaurantService.getAllBooking(customer);
    }
}

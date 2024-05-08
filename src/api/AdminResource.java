package api;

import model.IRestaurant;
import model.Owner;
import service.RestaurantService;
import service.UserService;

import java.util.List;

public class AdminResource {

    /**
     * This  method creates a new Owner object
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return Owner object
     */
    public static Owner createOwner(String firstName, String lastName, String email, String password) {
        return UserService.addOwner(firstName, lastName, email, password);
    }

    /**
     * This method returns Owner object associated with given email and password
     * @param email
     * @param password
     * @return Owner object
     */
    public static Owner getOwner(String email, String password) {
        return UserService.getOwner(email, password);
    }
    /**
     * This method registers a new restaurant under its owner
     * @param owner
     * @param restaurant
     */
    public static void registerRestaurant(Owner owner, IRestaurant restaurant) {
        RestaurantService.addRestaurant(owner, restaurant);
        System.out.println("SUCCESS!! Restaurant Registered.");
    }

    /**
     * This method returns the restaurant registered by the owner
     * @param owner
     * @return IRestaurant object
     */
    public static IRestaurant getRestaurant(Owner owner) {
        return RestaurantService.getRestaurant(owner);
    }

    /**
     * This method adds time slots for the specific restaurant
     * @param restaurant
     * @param openingHours
     * @param closingHours
     * @return list of time slots created with default 1 hour slot duration
     * */
    public static List<List<String>> addSlot(IRestaurant restaurant, String openingHours, String closingHours) {
        return RestaurantService.addSlot(restaurant, openingHours, closingHours);
    }



}

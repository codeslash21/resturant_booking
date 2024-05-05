package api;

import model.IRestaurant;
import model.Owner;
import service.RestaurantService;

import java.util.List;

public class AdminResource {
    /**
     * This method will register a new restaurant under its owner
     * @param owner
     * @param restaurant
     */
    public static void registerRestaurant(Owner owner, IRestaurant restaurant) {
        RestaurantService.addRestaurant(owner, restaurant);
    }

    /**
     * This method will add time slots for the specific restaurant
     * @param restaurant
     * @param openingHours
     * @param closingHours
     * @return list of time slots created with default 1 hour slot duration
     * */
    public static List<List<String>> addSlot(IRestaurant restaurant, String openingHours, String closingHours) {
        return RestaurantService.addSlot(restaurant, openingHours, closingHours);
    }



}

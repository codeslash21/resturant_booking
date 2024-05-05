package api;

import model.Customer;
import model.IRestaurant;
import service.RestaurantService;

import java.util.List;
import java.util.Map;

public class CustomerService {
    /**
     * This method will return list of restaurant that matches the search criteria
     * @param searchCriteria
     * @return list of restaurant
     */
    public static List<IRestaurant> searchRestaurant(Map<String, String> searchCriteria) {
        return RestaurantService.searchRestaurant(searchCriteria);
    }

    /**
     * This method will book table for a customer at a restaurant for n number of people at given slot
     * @param customer
     * @param restaurant
     * @param slot
     * @param people
     * */
    public static void bookTable(Customer customer, IRestaurant restaurant, List<String> slot, int people) {
        RestaurantService.bookTable(customer, restaurant, slot, people);
    }
}

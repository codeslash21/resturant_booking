package service;

import model.*;

import java.util.*;

public class RestaurantService {
    private static final Map<Owner, IRestaurant> restaurantMap = new HashMap<>();
    private static final Map<Customer, List<Booking>> bookingMap = new HashMap<>();
    private static final Map<IRestaurant, Map<List<String>, Integer>> timeSlotToCapacity = new HashMap<>();
    private static final Map<Customer, List<Booking>> customerBooking = new HashMap<>();



    /**
     * This method will register a restaurant under its owner
     * @param owner
     * @param restaurant
     */
    public static void addRestaurant(Owner owner, IRestaurant restaurant) {
            restaurantMap.put(owner, restaurant);
    }
    /**
    * This method will return restaurant object registered under a particular owner
    */
    public static IRestaurant getRestaurant(Owner owner) {
        return restaurantMap.get(owner);
    }

    public static List<IRestaurant> getAllRestaurant() {
        return (List<IRestaurant>) restaurantMap.values();
    }

    /**
     * This method will add time slots for a restaurant
     * @param restaurant
     * @param openingHours
     * @param closingHours
     * @return list of time slots created with default 1 hour slot duration
     */
    public static List<List<String>> addSlot(IRestaurant restaurant, String openingHours, String closingHours) {
        int openHour = Integer.parseInt(openingHours.split(":")[0]);
        String openMin = openingHours.split(":")[1];
        int closeHour = Integer.parseInt(closingHours.split(":")[0]);
        String closeMin = closingHours.split(":")[1];
        List<List<String>> timeSlots = new ArrayList<>();
        for(int i=openHour;i<=closeHour;i+=1) {
            String slotStart = i+openMin;
            String slotEnd = (i+1)+openMin;
            List<String> slot = new ArrayList<>();
            slot.add(slotStart);
            slot.add(slotEnd);
            timeSlots.add(slot);
        }
        String lastSlotEnd = timeSlots.get(timeSlots.size()-1).get(1);
        if(lastSlotEnd.compareTo(closingHours)>0) {
            timeSlots.remove(timeSlots.size()-1);
        }

        int capacity = restaurant.getCapacity();
        Map<List<String>, Integer> slotToCapacity = new HashMap();
        for(List<String> slot:timeSlots) {
            slotToCapacity.put(slot, capacity);
        }
        timeSlotToCapacity.put(restaurant, slotToCapacity);
        return timeSlots;
    }

    public static List<List<String>> getTimeSlots(IRestaurant restaurant, int seat) {
        Map<List<String>, Integer> slotToCapacity = timeSlotToCapacity.get(restaurant);
        List<List<String>> availableSlots = new ArrayList<>();
        for(Map.Entry<List<String>, Integer> entry : slotToCapacity.entrySet()) {
            if(entry.getValue()>=seat) {
                availableSlots.add(entry.getKey());
            }
        }
        return availableSlots;
    }

    public static void bookTable(Customer customer, IRestaurant restaurant, List<String> slot, int people) {
        Map<List<String>, Integer> slotToCapacity = timeSlotToCapacity.get(restaurant);
        slotToCapacity.put(slot, slotToCapacity.get(slot)-people);
        timeSlotToCapacity.put(restaurant, slotToCapacity);
        Booking booking = new Booking(customer, restaurant, slot);
        if(customerBooking.get(customer)==null) {
            customerBooking.put(customer, new ArrayList<>());
        }
        customerBooking.get(customer).add(booking);
    }

    public static List<IRestaurant> searchRestaurant(Map<String, String> searchCriteria) {
        List<IRestaurant> res = new ArrayList<>();
        List<IRestaurant> restaurantList = getAllRestaurant();
        for(IRestaurant restaurant:restaurantList) {
            boolean match=true;
            for(Map.Entry<String, String> entry:searchCriteria.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "City":
                        if(!restaurant.getCity().equalsIgnoreCase(value))
                            match=false;
                        break;
                    case "Area":
                        if(!restaurant.getArea().equalsIgnoreCase(value))
                            match=false;
                        break;
                    case "Cuisine":
                        if(!restaurant.getCuisine().equalsIgnoreCase(value))
                            match=false;
                        break;
                    case "Rating":
                        double rating = Double.parseDouble(value);
                        if(restaurant.getRating() < rating)
                            match=false;
                        break;
                    case "Cost":
                        int cost = (int) Double.parseDouble(value);
                        if(restaurant.getCost() > cost)
                            match=false;
                        break;
                    case "Food Type":
                        FoodType foodType = FoodType.valueOf(value);
                        if(!restaurant.getFoodType().equals(foodType))
                            match=false;
                    default:
                        break;
                }
                if(!match)
                    break;
            }
            if(match) {
                res.add(restaurant);
            }
        }
        return res;
    }

}

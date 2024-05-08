package service;

import model.*;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestaurantService {
    private static final Map<Owner, IRestaurant> restaurantMap = new HashMap<>();
    private static final Map<IRestaurant, Map<String, Map<List<String>, Integer>>> timeSlotToCapacity = new HashMap<>();
    private static final Map<Customer, List<Booking>> customerBooking = new HashMap<>();



    /**
     * This method registers a restaurant under its owner
     * @param owner
     * @param restaurant
     */
    public static void addRestaurant(Owner owner, IRestaurant restaurant) {
            restaurantMap.put(owner, restaurant);
    }

    /**
     * This method returns restaurant object registered under a particular owner
     * @param owner
     * @return IRestaurant object
     * @see IRestaurant
     */
    public static IRestaurant getRestaurant(Owner owner) {
        return restaurantMap.get(owner);
    }

    /**
     * This method returns all the registered restaurants
     * @return list of IRestaurant
     */
    public static List<IRestaurant> getAllRestaurant() {
        return new ArrayList<>(restaurantMap.values());
    }

    /**
     * This method returns all the bookings of a customer
     * @param customer
     * @return booking
     */
    public static List<Booking> getAllBooking(Customer customer) {
        List<Booking> booking = customerBooking.get(customer);
        return booking;
    }
    /**
     * This method adds time slots for a restaurant
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
        Map<List<String>, Integer> slotToCapacity = new HashMap<>();
        for(List<String> slot:timeSlots) {
            slotToCapacity.put(slot, capacity);
        }
        Map<String, Map<List<String>, Integer>> dateToSlots = new HashMap<>();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            LocalDate date = currentDate.plusDays(i);
            String formattedDate = date.format(formatter);
            dateToSlots.put(formattedDate, slotToCapacity);
        }
        timeSlotToCapacity.put(restaurant, dateToSlots);
        return timeSlots;
    }

    /**
     * This method returns available slots on a specific date for n number of people
     * @param restaurant
     * @param date
     * @param seat
     * @return availableSlots
     */
    public static List<List<String>> getTimeSlots(IRestaurant restaurant, String date, int seat) {
        Map<String, Map<List<String>, Integer>> dateToSlots = timeSlotToCapacity.get(restaurant);
        Map<List<String>, Integer> slotToCapacity = dateToSlots.get(date);
        List<List<String>> availableSlots = new ArrayList<>();
        for(Map.Entry<List<String>, Integer> entry : slotToCapacity.entrySet()) {
            if(entry.getValue()>=seat) {
                availableSlots.add(entry.getKey());
            }
        }
        return availableSlots;
    }

    /**
     * This method books slot in restaurant on a specific date for a customer
     * @param customer
     * @param restaurant
     * @param slot
     * @param date
     * @param people
     */
    public static void bookTable(Customer customer, IRestaurant restaurant, List<String> slot, String date, int people) {
        Map<String, Map<List<String>, Integer>> dateToSlots = timeSlotToCapacity.get(restaurant);
        Map<List<String>, Integer> slotToCapacity = dateToSlots.get(date);
        slotToCapacity.put(slot, slotToCapacity.get(slot)-people);
        Booking booking = new Booking(customer, restaurant, date, slot);
        if(customerBooking.get(customer)==null) {
            customerBooking.put(customer, new ArrayList<>());
        }
        customerBooking.get(customer).add(booking);
        System.out.println(booking.toString());
    }

    /**
     * This method searches restaurant based on user's given search criteria
     * @param searchCriteria
     * @return res
     */
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
                        if(!restaurant.getFoodType().equals(value))
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

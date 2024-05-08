package menu;

import api.AdminResource;
import helper.Helper;
//import model.FoodType;
import model.IRestaurant;
import model.Owner;
import model.Restaurant;

import java.util.*;

/**
 * This class is about to create admin menu and the functionalities corresponding to each menu option
 */
public class AdminMenu {
    /**
     * This method prints the Admin Panel to CLI
     */
    public static void adminMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("\n=================================");
        menuList.add("My Account");
        menuList.add("==================================");
        menuList.add("1. Register Restaurant");
        menuList.add("2. Add Time Slots");
        menuList.add("3. Restaurant Details.");
        menuList.add("4. Logout");
        menuList.add("----------------------------------");
        menuList.add("Please select an option");
        for(String str:menuList)
            System.out.println(str);
    }

    public static boolean executeMenuOption(Scanner scanner, Integer option, Owner owner) {
        boolean keepRunning = true;
        switch (option) {
            case 1:
                registerRestaurant(scanner, owner);
                break;
            case 2:
                addTimeSlots(scanner, owner);
                break;
            case 3:
                seeRestaurantDetails(scanner, owner);
                break;
            case 4:
                keepRunning = false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 3");
        }
        return keepRunning;
    }

    /**
     * This method registers a restaurant under an owner
     * @param scanner
     * @param owner
     */
    public static void registerRestaurant(Scanner scanner, Owner owner) {
        IRestaurant restaurant = AdminResource.getRestaurant(owner);
        if(restaurant!=null) {
            System.out.println("You already registered a restaurant.");
            System.out.println(restaurant);
            return;
        }
        boolean isValid=false;
        String name=null;
        String city=null;
        String area=null;
        String cuisine=null;
        Double cost=null;
        String foodType=null;
        int capacity=0;
        name = Helper.takeInput("Enter the name: ", scanner);
        city = Helper.takeInput("City: ", scanner);
        area = Helper.takeInput("Area: ", scanner);
        cuisine = Helper.takeInput("Cuisine: ", scanner);
        while(!isValid) {
            try {
                cost = Double.parseDouble(Helper.takeInput("Cost for two: ", scanner));
                isValid=true;
            } catch (Exception e) {
                System.out.println("Please Enter Integer Value.");
            }
        }
        foodType = Helper.takeInput("Food Type (VEG/NON-VEG): ", scanner);

        isValid=false;
        while (!isValid) {
            try {
                capacity = Integer.parseInt(Helper.takeInput("Restaurant Capacity: ", scanner));
                isValid=true;
            } catch (Exception e) {
                System.out.println("Please enter Integer Value.");
            }
        }

        restaurant = new Restaurant(name, city, area, cuisine, cost, foodType, capacity);
        AdminResource.registerRestaurant(owner, restaurant);
    }

    /**
     * This method adds time slots for a restaurant owned by the specific owner
     * @param scanner
     * @param owner
     */
    public static void addTimeSlots(Scanner scanner, Owner owner) {
        IRestaurant restaurant = AdminResource.getRestaurant(owner);
        if(restaurant==null) {
            System.out.println("You did not register your restaurant! Please register you restaurant with us.");
            return;
        }
        String openingHours = Helper.takeInput("Enter Opening Hours (HH:MM): ", scanner);
        String closingHours = Helper.takeInput("Enter Closing Hours (HH:MM): ", scanner);
        List<List<String>> slots = AdminResource.addSlot(restaurant, openingHours, closingHours);
        System.out.println("Slots are: ");
        for(int i=0;i<slots.size();i++) {
            List<String> slot = slots.get(i);
            System.out.println(slot.get(0)+" - "+slot.get(1));
        }
    }

    /**
     * This method shows the restaurant registered by an owner
     * @param scanner
     * @param owner
     */
    public static void seeRestaurantDetails(Scanner scanner, Owner owner) {
        IRestaurant restaurant = AdminResource.getRestaurant(owner);
        if(restaurant==null) {
            System.out.println("You did not register any restaurant yet.");
            return;
        }
        System.out.println("\nHere is your restaurant details.");
        System.out.println("-------------------------------------------");
        System.out.println(restaurant.toString());
    }
}

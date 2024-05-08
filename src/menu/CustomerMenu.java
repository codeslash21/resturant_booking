package menu;

import api.CustomerResource;
import helper.Helper;
import model.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomerMenu {
    private static List<IRestaurant> restaurantList=new ArrayList<>();
    private static String userChosenDate=null;
    private static List<List<String>>  timeSlots=new ArrayList<>();
    public static void customerMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("\n=================================");
        menuList.add("Welcome to Food Space");
        menuList.add("==================================");
        menuList.add("1. Search Restaurant");
        menuList.add("2. Book Table");
        menuList.add("3. See Booking");
        menuList.add("4. Logout");
        menuList.add("----------------------------------");
        menuList.add("Please select an option");
        for(String str:menuList)
            System.out.println(str);
    }

    public static boolean executeMenuOption(Scanner scanner, Integer option, Customer customer) {
        boolean keepRunning = true;
        switch (option) {
            case 1:
                searchRestaurant(scanner, customer);
                break;
            case 2:
                bookTable(scanner, customer);
                break;
            case 3:
                seeBooking(scanner, customer);
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
     * This method searches restaurants for given search criteria by custoemr
     * @param scanner
     * @param customer
     */
    public static void searchRestaurant(Scanner scanner, Customer customer) {
        String[] searchCriteria = {"Name", "City", "Area", "Cuisine", "Rating", "Cost", "Food Type"};
        System.out.println("Give value for the following search criteria.\n If you dont want to search by any parameter then press Enter");
        Map<String, String> searchMap = new HashMap<>();
        for(String ele:searchCriteria) {
            String value = Helper.takeInput(ele+": ", scanner);
            searchMap.put(ele.toLowerCase(), value.toLowerCase());
        }
        userChosenDate=null;
        restaurantList=null;
        restaurantList = CustomerResource.searchRestaurant(searchMap);
        if(restaurantList.size()==0) {
            System.out.println("\nSorry!! We did not find any restaurant.");
            return;
        }
        int i=1;
        System.out.println("\nHere is your restaurants ->\n");
        System.out.println("======================================");
        for(IRestaurant restaurant:restaurantList) {
            System.out.println("Restaurant "+i+":");
            System.out.println("------------------------------");
            System.out.println(restaurant.toString());
            i+=1;
        }
    }

    /**
     * This method books a slot for customer at a specific restaurant on a specific date
     * @param scanner
     * @param customer
     */
    public static void bookTable(Scanner scanner, Customer customer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Comparator<List<String>> customComparator = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> list1, List<String> list2) {
                return Integer.compare(Integer.valueOf(list1.get(0).split(":")[0]),
                        Integer.valueOf(list2.get(0).split(":")[0]));
            }
        };
        if(restaurantList.size()==0) {
            System.out.println("You did not choose any restaurant. Please search restaurant before booking");
            return;
        }
        int idx=0;
        boolean isValid=false;
        while(!isValid) {
            try {
                idx = Integer.parseInt(Helper.takeInput("Please choose the restaurant by giving restaurant number: ", scanner));
                if(idx>restaurantList.size()) {
                    System.out.println("Please choose a number between 1 and "+restaurantList.size());
                } else {
                    isValid=true;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }

        }
        IRestaurant restaurant = restaurantList.get(idx-1);
        isValid=false;
        int people = 0;
        while(!isValid) {
            try {
                String strDate = Helper.takeInput("Please give date (YYYY-MM-DD): ", scanner);
                Date date = dateFormat.parse(strDate);
                LocalDate userDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate currDate = LocalDate.now();
                long daysDifference = ChronoUnit.DAYS.between(currDate, userDate);
                if(daysDifference<0) {
                    System.out.println("You cant book in the past.");
                    continue;
                }
                if(daysDifference<=7) {
                    isValid=true;
                    String formattedDate = userDate.format(formatter);
                    userChosenDate = formattedDate;
                    people = Integer.parseInt(Helper.takeInput("How many people you want to book for? ", scanner));
                    timeSlots = CustomerResource.getTimeSlots(restaurant, userChosenDate, people);
                    Collections.sort(timeSlots, customComparator);
                    System.out.println("Following slots are available for "+people+" people.");
                    int i=1;
                    for(List<String> slot:timeSlots) {
                        System.out.println("Slot "+i+": "+slot.get(0)+" "+slot.get(1));
                        i+=1;
                    }
                } else {
                    System.out.println("Bookings only allowed for 7 days in future\n");
                }
            } catch (ParseException e) {
                System.out.println("Please enter the date in YYYY-MM-DD format");
            }
        }
        int  slotIdx=0;
        isValid=false;
        while (!isValid) {
            try {
                slotIdx = Integer.parseInt(Helper.takeInput("Choose your preferred slot (Give slot number): ", scanner));
                if(slotIdx>timeSlots.size()) {
                    System.out.println("Please enter a number between 1 and "+timeSlots.size());
                } else {
                    isValid=true;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }
        }
        List<String> slot = timeSlots.get(slotIdx-1);
        CustomerResource.bookTable(customer, restaurant, slot, userChosenDate, people);
    }

    /**
     * This method returns all the bookings of a customer
     * @param scanner
     * @param customer
     */
    public static void seeBooking(Scanner scanner, Customer customer) {
        List<Booking> bookings = CustomerResource.getAllBooking(customer);
        if(bookings==null) {
            System.out.println("You dont have any booking at this moment.");
            return;
        }
        int i=1;
        System.out.println("\nHere is the list of all your booking with us.\n");
        for(Booking booking:bookings) {
            System.out.println("Booking "+i+":");
            System.out.println("-----------------------");
            System.out.println(booking.toString());
            i+=1;
        }
    }
}

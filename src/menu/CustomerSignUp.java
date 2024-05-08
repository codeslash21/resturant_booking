package menu;

import api.CustomerResource;
import helper.Helper;
import model.Customer;
import model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerSignUp {
    public static void mainMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("\n=================================");
        menuList.add("Food Is Waiting For You");
        menuList.add("=================================");
        menuList.add("1. Login");
        menuList.add("2. Sign Up");
        menuList.add("3. Exit");
        menuList.add("----------------------------------");
        menuList.add("Please select an option");
        for(String str:menuList)
            System.out.println(str);
    }

    public static boolean executeMainMenu(Scanner scanner, Integer option) {
        boolean keepRunning = true;
        switch (option) {
            case 1:
                executeLogin(scanner);
                break;
            case 2:
                executeSignUp(scanner);
                break;
            case 3:
                keepRunning=false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 3");
        }
        return keepRunning;
    }

    /**
     * This method will let the Customer sign up
     * @param scanner
     */
    public static void executeSignUp(Scanner scanner) {
        String[] details = Helper.signUp(scanner);
        Customer customer = CustomerResource.createCustomer(details[0], details[1], details[2], details[3]);
        executeCustomerMenu(scanner, customer);
    }

    /**
     * This method will let the Customer login
     * @param scanner
     */
    public static void executeLogin(Scanner scanner) {
        boolean keepRunning = true;
        Customer customer = null;
        while(keepRunning) {
            String[] details = Helper.logIn(scanner);
            customer = CustomerResource.getCustomer(details[0], details[1]);
            if(customer!=null) {
                keepRunning=false;
            } else {
//                System.out.println("Login credentials are incorrect!");
                return;
            }
        }
        executeCustomerMenu(scanner, customer);
    }

    /**
     * This method will let the logged in Customer perform task from customerMenu
     * @param scanner
     * @param customer
     */
    public static void executeCustomerMenu(Scanner scanner, Customer customer) {
        System.out.println("\n----------------------------");
        System.out.println("Welcome!! "+customer.toString());
        System.out.println("----------------------------");
        boolean keepRunning = true;
        while(keepRunning) {
            CustomerMenu.customerMenu();
            Integer option = Integer.parseInt(scanner.nextLine());
            keepRunning = CustomerMenu.executeMenuOption(scanner, option, customer);
        }
    }
}

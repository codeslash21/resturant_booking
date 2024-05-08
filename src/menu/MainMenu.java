package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void mainMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("\n=================================");
        menuList.add("Welcome to FoodCourt");
        menuList.add("=================================");
        menuList.add("1. Restaurant Owner");
        menuList.add("2. Foodie");
        menuList.add("3. Exit");
        menuList.add("----------------------------------");
        menuList.add("Please select an option");
        for(String str:menuList)
            System.out.println(str);
    }

    /**
     * This method will let the user perform task from mainMenu depending on the given input
     * @param scanner
     * @param option
     * @return showMenu
     */
    public static boolean executeMainMenuOption(Scanner scanner, Integer option) {
        boolean showMenu = true;
        switch (option) {
            case 1:
                showOwnerSignUp(scanner);
                break;
            case 2:
                showCustomerSignUp(scanner);
                break;
            case 3:
                showMenu = false;
                break;
            default:
                System.out.println("Please choose a number between 1 to 3");
        }
        return showMenu;
    }

    /**
     * This method opens Owner SignUp/Login option
     * @param scanner
     */
    public static void showOwnerSignUp(Scanner scanner) {
        boolean keepRunning = true;
        while(keepRunning) {
            try {
                OwnerSignUp.mainMenu();
                Integer option = Integer.parseInt(scanner.nextLine());
                keepRunning = OwnerSignUp.executeMainMenu(scanner, option);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 to 3");
            }
        }
    }

    /**
     * This method opens Customer SignUp/Login option
     * @param scanner
     */
    public static void showCustomerSignUp(Scanner scanner) {
        boolean keepRunning = true;
        while(keepRunning) {
            try {
                CustomerSignUp.mainMenu();
                Integer option = Integer.parseInt(scanner.nextLine());
                keepRunning = CustomerSignUp.executeMainMenu(scanner, option);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 to 5");
            }
        }
    }
}

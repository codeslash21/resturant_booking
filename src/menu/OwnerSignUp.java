package menu;

import api.AdminResource;
import helper.Helper;
import model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OwnerSignUp {
    public static void mainMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("\n=================================");
        menuList.add("Welcome to Owner Space");
        menuList.add("=================================");
        menuList.add("1. Login");
        menuList.add("2. Sign Up");
        menuList.add("3. Exit");
        menuList.add("----------------------------------");
        menuList.add("Please select an option");
        for(String str:menuList)
            System.out.println(str);
    }

    /**
     * This method will perform task from the menu option depending on the owner's input
     * @param scanner
     * @param option
     * @return keepRunning
     */
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
     * This method will let the Owner sign up to the application
     * @param scanner
     */
    public static void executeSignUp(Scanner scanner) {
        String[] details = Helper.signUp(scanner);
        Owner owner = AdminResource.createOwner(details[0], details[1], details[2], details[3]);
        executeAdminMenu(scanner, owner);
    }

    /**
     * This method will let the owner login to the application
     * @param scanner
     */
    public static void executeLogin(Scanner scanner) {
        boolean keepRunning = true;
        Owner owner = null;
        while(keepRunning) {
            String[] details = Helper.logIn(scanner);
            owner = AdminResource.getOwner(details[0], details[1]);
            if(owner!=null) {
                keepRunning=false;
            } else {
                return;
            }
        }
        executeAdminMenu(scanner, owner);
    }

    /**
     * This method will let the owner perform task from the adminMenu
     * @param scanner
     * @param owner
     * @see AdminMenu
     */
    public static void executeAdminMenu(Scanner scanner, Owner owner) {
        System.out.println("\n----------------------------");
        System.out.println("Welcome!! "+owner.toString());
        System.out.println("----------------------------");
        boolean keepRunning = true;
        while(keepRunning) {
            AdminMenu.adminMenu();
            Integer option = Integer.parseInt(scanner.nextLine());
            keepRunning = AdminMenu.executeMenuOption(scanner, option, owner);
        }
    }
}

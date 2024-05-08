package service;

import model.Customer;
import model.Owner;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, Customer> customerMap = new HashMap<>();
    private static final Map<String, String> customerLoginMap = new HashMap<>();
    private static final Map<String, Owner> ownerMap = new HashMap<>();
    private static final Map<String, String> ownerLoginMap = new HashMap<>();

    /**
     * This method adds a new customer to record.
     * @param firstName
     * @param lastName
     * @param email
     */
    public static Customer addCustomer(String firstName, String lastName, String email, String password) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(email, customer);
        customerLoginMap.put(email, password);
        return customer;
    }

    /**
     * This method adds a new owner to record.

     * @param firstName
     * @param lastName
     * @param email
     */
    public static Owner addOwner(String firstName, String lastName, String email, String password) {
        Owner owner = new Owner(firstName, lastName, email);
        ownerMap.put(email, owner);
        ownerLoginMap.put(email,password);
        return owner;
    }

    /**
     * This method returns customer which is associated with the given email
     * @param email
     * @return Customer object
     * @see model.Customer
     */
    public static Customer getCustomer(String email, String password) {
        if(!customerLoginMap.containsKey(email)) {
            System.out.println("This email address is not registered with us!");
            return null;
        }
        if(!password.equals(customerLoginMap.get(email))) {
            System.out.println("Wrong Password.");
            return null;
        }
        return customerMap.get(email);
    }



    /**
     * This method returns owner which is associated with the given email
     * @param email
     * @return Owner object
     * @see model.Customer
     */
    public static Owner getOwner(String email, String password) {
        if(!ownerLoginMap.containsKey(email)) {
            System.out.println("This email address is not registered with us!");
            return null;
        }
        if(!password.equals(ownerLoginMap.get(email))) {
            System.out.println("Wrong Password.");
            return null;
        }
        return ownerMap.get(email);
    }
}

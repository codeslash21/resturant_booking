package service;

import model.Customer;
import model.Owner;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, Customer> customerMap = new HashMap<>();
    private static final Map<String, Owner> ownerMap = new HashMap<>();

    /**
     * This method will add a new customer to record.
     * @param firstName
     * @param lastName
     * @param email
     */
    public static void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(email, customer);
    }

    /**
     * This method will add a new owner to record.

     * @param firstName
     * @param lastName
     * @param email
     */
    public static void addOwner(String email, String firstName, String lastName) {
        Owner owner = new Owner(firstName, lastName, email);
        ownerMap.put(email, owner);
    }

    /**
     * This method will return customer which is associated with the given email
     * @param email
     * @return Customer object
     * @see model.Customer
     */
    public static Customer getCustomer(String email) {
        Customer customer = customerMap.getOrDefault(email, null);
        if(customer==null) {
            throw new IllegalArgumentException("Email does not exist!");
        }
        return customer;
    }

    /**
     * This method will return owner which is associated with the given email
     * @param email
     * @return Owner object
     * @see model.Customer
     */
    public static Owner getOwner(String email) {
        Owner owner = ownerMap.getOrDefault(email, null);
        if(owner==null) {
            throw new IllegalArgumentException("Email does not exist!");
        }
        return owner;
    }
}

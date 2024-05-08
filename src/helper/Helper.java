package helper;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Helper {
    /**
     * This method will encrypt the given text
     * @param text
     * @return encryptedText
     */
    public static String encrypt(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            String encryptedText = new String(hash);
            return encryptedText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will let the user sign up to the application
     * @param scanner
     * @return res
     */
    public static String[] signUp(Scanner scanner) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        System.out.println("\nPlease enter your details 👇");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        String email = null;
        boolean isValid=false;
        while(!isValid) {
            System.out.print("Email(name@example.com): ");
            email = scanner.nextLine();
            if(email.matches(emailRegex)) {
                isValid=true;
            } else {
                System.out.println("Please give valid email address.");
            }
        }
        System.out.print("Password: ");
        String password = Helper.encrypt(scanner.nextLine());

        String[] res = {firstName, lastName, email, password};

        return res;
    }

    /**
     * This method will let the user to log in the application
     * @param scanner
     * @return res
     */
    public static String[] logIn(Scanner scanner) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        String email = null;
        boolean isValid=false;
        while(!isValid) {
            System.out.print("Email(name@example.com): ");
            email = scanner.nextLine();
            if(email.matches(emailRegex)) {
                isValid=true;
            } else {
                System.out.println("Please give valid email address.");
            }
        }
        System.out.print("Password: ");
        String password = Helper.encrypt(scanner.nextLine());

        String[] res = {email, password};

        return res;
    }

    /**
     * This method will take input from the console
     * @param text
     * @param scanner
     * @return string
     */
    public static String takeInput(String text, Scanner scanner) {
        System.out.print(text);
        return scanner.nextLine();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Luu Bach
 */
import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHashing {

    public static String hashPassword(String plainTextPassword) {
        // Generate a salt
        String salt = BCrypt.withDefaults().hashToString(12, plainTextPassword.toCharArray());

        // Hash the password with the generated salt
        return BCrypt.withDefaults().hashToString(12, plainTextPassword.toCharArray());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        // Check if the plain text password matches the hashed password
        return BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hashedPassword).verified;
    }

    public static void main(String[] args) {
        String userPassword = "123456789"; // Replace this with the actual user's password

        // Hash the password before storing it in the database
        String hashedPassword = hashPassword(userPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // Example of checking a password during authentication
        boolean passwordMatch = checkPassword("123", hashedPassword);
        System.out.println("Password Match: " + passwordMatch);
    }
}

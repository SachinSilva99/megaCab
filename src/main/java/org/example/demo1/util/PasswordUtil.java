package org.example.demo1.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash the password
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(4)); // 12 is the salt rounds
    }

    // Verify the password
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "mySecurePassword";

        // Hashing
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verifying
        boolean isMatch = checkPassword(password, hashedPassword);
        System.out.println("Password Match: " + isMatch);
    }
}

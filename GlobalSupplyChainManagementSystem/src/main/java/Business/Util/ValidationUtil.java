package Business.Util;

import java.util.regex.Pattern;

/**
 * 
 * @author sange
 */
public class ValidationUtil {
    
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }
    
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // Name should be 2-50 characters, letters, spaces, hyphens, apostrophes only
        return name.matches("^[a-zA-Z\\s'-]{2,50}$");
    }
    
    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 100;
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // Accepts formats: (123) 456-7890, 123-456-7890, 123.456.7890, 1234567890
        return phone.replaceAll("[\\s()-.]", "").matches("^\\d{10}$");
    }
    
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        // At least 8 characters, contains at least one digit, one lowercase, one uppercase
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        return hasDigit && hasLower && hasUpper;
    }
    
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // Username: 3-20 characters, alphanumeric and underscore only
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
    
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0 && quantity <= 1000000;
    }
    
    public static boolean isValidPrice(double price) {
        return price >= 0 && price <= 10000000;
    }
    
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        // Remove potentially dangerous characters
        return input.trim().replaceAll("[<>\"'&]", "");
    }
}


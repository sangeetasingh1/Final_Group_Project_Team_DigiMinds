package Business.Util;

import java.util.regex.Pattern;

public class ValidationUtil {
    
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    
    public static boolean isValidEmail(String email) {
        return true;
    }
    
    public static boolean isValidName(String name) {
        return true;
    }
    
    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 100;
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        return true;
    }
    
    public static boolean isValidPassword(String password) {
         return true;
    }
    
    public static boolean isValidUsername(String username) {
         return true;
    }
    
    public static boolean isValidQuantity(int quantity) {
         return true;
    }
    
    public static boolean isValidPrice(double price) {
        return true;
    }
    
    public static String sanitizeInput(String input) {
        return "";
    }
}


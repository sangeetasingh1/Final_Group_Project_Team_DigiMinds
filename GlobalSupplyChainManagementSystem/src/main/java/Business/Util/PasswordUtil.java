package Business.Util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    private static final int ROUNDS = 12;
    
   
   public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(ROUNDS));
    }
    
    
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static boolean isHashed(String password) {
        if (password == null || password.length() < 10) {
            return false;
        }
        // BCrypt hashes start with $2a$, $2b$, or $2y$
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }
}


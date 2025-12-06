package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 * 
 */
public class LogisticsEnterprise extends Enterprise {
    
    public LogisticsEnterprise(String name){
        super(name, EnterpriseType.Logistics);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null; // Enterprise-level roles handled by organizations
    }
}


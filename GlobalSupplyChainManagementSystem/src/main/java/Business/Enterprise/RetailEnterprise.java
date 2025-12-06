package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 * 
 */
public class RetailEnterprise extends Enterprise {
    
    public RetailEnterprise(String name){
        super(name, EnterpriseType.Retail);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null; // Enterprise-level roles handled by organizations
    }
}


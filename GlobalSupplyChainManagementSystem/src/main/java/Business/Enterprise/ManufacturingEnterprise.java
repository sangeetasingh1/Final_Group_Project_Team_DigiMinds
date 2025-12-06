package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 * 
 */
public class ManufacturingEnterprise extends Enterprise {
    
    public ManufacturingEnterprise(String name){
        super(name, EnterpriseType.Manufacturing);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null; // Enterprise-level roles handled by organizations
    }
}


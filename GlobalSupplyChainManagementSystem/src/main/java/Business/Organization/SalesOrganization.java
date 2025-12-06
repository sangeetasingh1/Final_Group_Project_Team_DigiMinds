package Business.Organization;

import Business.Role.SalesRepRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 */
public class SalesOrganization extends Organization {

    public SalesOrganization() {
        super(Type.Sales.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new SalesRepRole());
        return roles;
    }
}


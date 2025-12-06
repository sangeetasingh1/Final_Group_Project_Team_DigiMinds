package Business.Organization;

import Business.Role.ProductionManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 */
public class ProductionOrganization extends Organization {

    public ProductionOrganization() {
        super(Type.Production.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new ProductionManagerRole());
        return roles;
    }
}


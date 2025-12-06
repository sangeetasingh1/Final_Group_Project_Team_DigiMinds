package Business.Organization;

import Business.Role.ShippingCoordinatorRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 */
public class ShippingOrganization extends Organization {

    public ShippingOrganization() {
        super(Type.Shipping.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new ShippingCoordinatorRole());
        return roles;
    }
}


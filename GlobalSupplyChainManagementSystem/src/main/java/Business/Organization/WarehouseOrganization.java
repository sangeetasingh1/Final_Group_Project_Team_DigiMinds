package Business.Organization;

import Business.Role.WarehouseManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 */
public class WarehouseOrganization extends Organization {

    public WarehouseOrganization() {
        super(Type.Warehouse.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new WarehouseManagerRole());
        return roles;
    }
}


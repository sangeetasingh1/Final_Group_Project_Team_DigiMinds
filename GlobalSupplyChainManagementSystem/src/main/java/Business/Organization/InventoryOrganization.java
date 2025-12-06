package Business.Organization;

import Business.Role.InventoryAnalystRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 * 
 */
public class InventoryOrganization extends Organization {

    public InventoryOrganization() {
        super(Type.Inventory.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new InventoryAnalystRole());
        return roles;
    }
}


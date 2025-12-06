package Business.Organization;

import Business.Role.QualityInspectorRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 * 
 * @author sange
 */
public class QualityControlOrganization extends Organization {

    public QualityControlOrganization() {
        super(Type.QualityControl.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new QualityInspectorRole());
        return roles;
    }
}


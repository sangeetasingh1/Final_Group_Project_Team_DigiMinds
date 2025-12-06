package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import ui.WarehouseRole.WarehouseManagerWorkAreaJPanel;
import javax.swing.JPanel;

public class WarehouseManagerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new WarehouseManagerWorkAreaJPanel(userProcessContainer, account, (WarehouseOrganization)organization, enterprise, business);
    }
}


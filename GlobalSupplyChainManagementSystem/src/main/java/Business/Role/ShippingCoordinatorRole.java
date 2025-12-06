package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.ShippingOrganization;
import Business.UserAccount.UserAccount;
import ui.ShippingRole.ShippingCoordinatorWorkAreaJPanel;
import javax.swing.JPanel;

public class ShippingCoordinatorRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new ShippingCoordinatorWorkAreaJPanel(userProcessContainer, account, (ShippingOrganization)organization, enterprise, business);
    }
}


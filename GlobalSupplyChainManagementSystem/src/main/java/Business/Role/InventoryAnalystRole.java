package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.InventoryOrganization;
import Business.UserAccount.UserAccount;
import ui.InventoryRole.InventoryAnalystWorkAreaJPanel;
import javax.swing.JPanel;


public class InventoryAnalystRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new InventoryAnalystWorkAreaJPanel(userProcessContainer, account, (InventoryOrganization)organization, enterprise, business);
    }
}


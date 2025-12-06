package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.ProductionOrganization;
import Business.UserAccount.UserAccount;
import ui.ProductionRole.ProductionManagerWorkAreaJPanel;
import javax.swing.JPanel;

public class ProductionManagerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new ProductionManagerWorkAreaJPanel(userProcessContainer, account, (ProductionOrganization)organization, enterprise, business);
    }
}


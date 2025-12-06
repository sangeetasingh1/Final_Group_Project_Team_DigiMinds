package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.SalesOrganization;
import Business.UserAccount.UserAccount;
import ui.SalesRole.SalesRepWorkAreaJPanel;
import javax.swing.JPanel;

public class SalesRepRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new SalesRepWorkAreaJPanel(userProcessContainer, account, (SalesOrganization)organization, enterprise, business);
    }
}


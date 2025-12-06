package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import ui.SupplyChainRole.SupplyChainAnalystWorkAreaJPanel;
import javax.swing.JPanel;

public class SupplyChainAnalystRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new SupplyChainAnalystWorkAreaJPanel(userProcessContainer, account, organization, enterprise, business);
    }
}


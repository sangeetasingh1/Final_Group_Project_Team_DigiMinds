package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.ProductionOrganization;
import Business.UserAccount.UserAccount;
import ui.ProcurementRole.ProcurementOfficerWorkAreaJPanel;
import javax.swing.JPanel;

public class ProcurementOfficerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new ProcurementOfficerWorkAreaJPanel(userProcessContainer, account, organization, enterprise, business);
    }
}


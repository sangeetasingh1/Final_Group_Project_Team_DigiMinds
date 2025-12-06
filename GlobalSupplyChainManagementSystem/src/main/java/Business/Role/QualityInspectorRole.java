package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.QualityControlOrganization;
import Business.UserAccount.UserAccount;
import ui.QualityControlRole.QualityInspectorWorkAreaJPanel;
import javax.swing.JPanel;

public class QualityInspectorRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new QualityInspectorWorkAreaJPanel(userProcessContainer, account, (QualityControlOrganization)organization, enterprise, business);
    }
}


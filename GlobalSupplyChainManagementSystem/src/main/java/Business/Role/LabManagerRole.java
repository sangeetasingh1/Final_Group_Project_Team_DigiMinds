package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.LabManagerRole.LabManagerWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class LabManagerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new LabManagerWorkAreaJPanel(userProcessContainer, account, organization, business);
    }
    
}

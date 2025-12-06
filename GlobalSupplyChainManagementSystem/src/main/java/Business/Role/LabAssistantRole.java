package Business.Role;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import ui.LabAssistantRole.LabAssistantWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author sange
 */
public class LabAssistantRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        return new LabAssistantWorkAreaJPanel(userProcessContainer, account, organization, business);
    }
    
}

package ui.ProductionRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.QualityControlOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.QualityInspectionRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateQualityInspectionJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private SupplyChainSystem business;
    private ProductionManagerWorkAreaJPanel parentPanel;

    public CreateQualityInspectionJPanel(JPanel userProcessContainer, UserAccount account,
                                        Enterprise enterprise, SupplyChainSystem business, ProductionManagerWorkAreaJPanel parentPanel) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.enterprise = enterprise;
        this.business = business;
        this.parentPanel = parentPanel;
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(600, 350));
        this.setMinimumSize(new java.awt.Dimension(550, 320));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        productIdJTextField = new javax.swing.JTextField();
        productNameJTextField = new javax.swing.JTextField();
        inspectionTypeJComboBox = new javax.swing.JComboBox<>();
        messageJTextArea = new javax.swing.JTextArea();
        createJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Create Quality Inspection Request");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 25));

        add(new javax.swing.JLabel("Product ID:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 150, 20));
        add(productIdJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 350, 25));
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 150, 20));
        add(productNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 350, 25));

        add(new javax.swing.JLabel("Inspection Type:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 20));
        inspectionTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pre-Production", "In-Process", "Final", "Random"}));
        add(inspectionTypeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 105, 200, 25));

        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(4);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 135, 350, 80));

        createJButton.setText("Create Request");
        createJButton.addActionListener(evt -> createJButtonActionPerformed());
        add(createJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> backJButtonActionPerformed());
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 100, 30));
    }

    private void createJButtonActionPerformed() {
        String productId = productIdJTextField.getText().trim();
        String productName = productNameJTextField.getText().trim();
        String message = messageJTextArea.getText().trim();

        // Validation
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter product ID");
            return;
        }
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter product name");
            return;
        }

        // Find Quality Control Organization (same enterprise)
        QualityControlOrganization qualityOrg = null;
        for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof QualityControlOrganization) {
                qualityOrg = (QualityControlOrganization) org;
                break;
            }
        }

        if (qualityOrg == null) {
            JOptionPane.showMessageDialog(null, "Quality Control Organization not found");
            return;
        }

        // Create Quality Inspection Request
        QualityInspectionRequest request = new QualityInspectionRequest();
        request.setProductId(productId);
        request.setProductName(productName);
        request.setInspectionType((String) inspectionTypeJComboBox.getSelectedItem());
        request.setMessage(message.isEmpty() ? "Quality inspection required for " + productName : message);
        request.setSender(userAccount);
        request.setStatus("Sent");

        // Add to Quality Control Organization queue and user's queue
        qualityOrg.getWorkQueue().getWorkRequestList().add(request);
        userAccount.getWorkQueue().getWorkRequestList().add(request);

        JOptionPane.showMessageDialog(null, "Quality inspection request created successfully!");
        backJButtonActionPerformed();
    }

    private void backJButtonActionPerformed() {
        userProcessContainer.remove(this);
        if (parentPanel != null) {
            parentPanel.populateRequestTable();
        }
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }

    private javax.swing.JButton backJButton;
    private javax.swing.JButton createJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> inspectionTypeJComboBox;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JTextField productIdJTextField;
    private javax.swing.JTextField productNameJTextField;
}


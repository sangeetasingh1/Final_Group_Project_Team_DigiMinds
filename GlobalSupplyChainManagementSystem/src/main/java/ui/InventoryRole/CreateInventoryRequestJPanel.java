package ui.InventoryRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Util.ValidationUtil;
import Business.WorkQueue.InventoryRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateInventoryRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private SupplyChainSystem business;
    private InventoryAnalystWorkAreaJPanel parentPanel;

    public CreateInventoryRequestJPanel(JPanel userProcessContainer, UserAccount account,
                                        Enterprise enterprise, SupplyChainSystem business, InventoryAnalystWorkAreaJPanel parentPanel) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.enterprise = enterprise;
        this.business = business;
        this.parentPanel = parentPanel;
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(600, 400));
        this.setMinimumSize(new java.awt.Dimension(550, 380));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        productIdJTextField = new javax.swing.JTextField();
        productNameJTextField = new javax.swing.JTextField();
        requestedQuantityJTextField = new javax.swing.JTextField();
        requestTypeJComboBox = new javax.swing.JComboBox<>();
        warehouseLocationJTextField = new javax.swing.JTextField();
        notesJTextArea = new javax.swing.JTextArea();
        createJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Create Inventory Request");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 25));

        add(new javax.swing.JLabel("Product ID:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 150, 20));
        add(productIdJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 350, 25));
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 150, 20));
        add(productNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 350, 25));
        
        add(new javax.swing.JLabel("Requested Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 20));
        add(requestedQuantityJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 105, 150, 25));

        add(new javax.swing.JLabel("Request Type:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 150, 20));
        requestTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Stock Check", "Restock", "Transfer", "Adjustment"}));
        add(requestTypeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 135, 200, 25));
        
        add(new javax.swing.JLabel("Warehouse Location:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 150, 20));
        add(warehouseLocationJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 165, 350, 25));

        add(new javax.swing.JLabel("Notes:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, 150, 20));
        notesJTextArea.setColumns(20);
        notesJTextArea.setRows(4);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(notesJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 195, 350, 80));

        createJButton.setText("Create Request");
        createJButton.addActionListener(evt -> createJButtonActionPerformed());
        add(createJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> backJButtonActionPerformed());
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 100, 30));
    }

    private void createJButtonActionPerformed() {
        String productId = productIdJTextField.getText().trim();
        String productName = productNameJTextField.getText().trim();
        String quantityStr = requestedQuantityJTextField.getText().trim();
        String warehouseLocation = warehouseLocationJTextField.getText().trim();
        String notes = notesJTextArea.getText().trim();

        // Validation
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter product ID");
            return;
        }
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter product name");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be greater than 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity");
            return;
        }

        // Find Warehouse Organization
        WarehouseOrganization warehouseOrg = null;
        for (Network network : business.getNetworkList()) {
            for (Business.Enterprise.Enterprise ent : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (ent instanceof Business.Enterprise.LogisticsEnterprise) {
                    for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                        if (org instanceof WarehouseOrganization) {
                            warehouseOrg = (WarehouseOrganization) org;
                            break;
                        }
                    }
                }
                if (warehouseOrg != null) break;
            }
            if (warehouseOrg != null) break;
        }

        if (warehouseOrg == null) {
            JOptionPane.showMessageDialog(null, "Warehouse Organization not found");
            return;
        }

        // Create Inventory Request
        InventoryRequest request = new InventoryRequest();
        request.setProductId(productId);
        request.setProductName(productName);
        request.setRequestedQuantity(quantity);
        request.setRequestType((String) requestTypeJComboBox.getSelectedItem());
        request.setWarehouseLocation(warehouseLocation.isEmpty() ? "N/A" : warehouseLocation);
        request.setNotes(notes);
        request.setMessage("Inventory request: " + requestTypeJComboBox.getSelectedItem() + " for " + productName);
        request.setInventoryStatus("Pending");
        request.setSender(userAccount);
        request.setStatus("Sent");

        // Add to Warehouse Organization queue and user's queue
        warehouseOrg.getWorkQueue().getWorkRequestList().add(request);
        userAccount.getWorkQueue().getWorkRequestList().add(request);

        JOptionPane.showMessageDialog(null, "Inventory request created successfully!");
        backJButtonActionPerformed();
    }

    private void backJButtonActionPerformed() {
        userProcessContainer.remove(this);
        if (parentPanel != null) {
            parentPanel.populateTable();
        }
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }

    private javax.swing.JButton backJButton;
    private javax.swing.JButton createJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea notesJTextArea;
    private javax.swing.JTextField productIdJTextField;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField requestedQuantityJTextField;
    private javax.swing.JComboBox<String> requestTypeJComboBox;
    private javax.swing.JTextField warehouseLocationJTextField;
}


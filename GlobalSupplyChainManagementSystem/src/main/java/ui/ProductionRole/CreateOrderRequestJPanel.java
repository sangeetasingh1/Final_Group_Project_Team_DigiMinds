package ui.ProductionRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Util.ValidationUtil;
import Business.WorkQueue.OrderRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateOrderRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private SupplyChainSystem business;
    private ProductionManagerWorkAreaJPanel parentPanel;

    public CreateOrderRequestJPanel(JPanel userProcessContainer, UserAccount account,
                                   Enterprise enterprise, SupplyChainSystem business, ProductionManagerWorkAreaJPanel parentPanel) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.enterprise = enterprise;
        this.business = business;
        this.parentPanel = parentPanel;
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(600, 450));
        this.setMinimumSize(new java.awt.Dimension(550, 400));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        productNameJTextField = new javax.swing.JTextField();
        quantityJTextField = new javax.swing.JTextField();
        unitPriceJTextField = new javax.swing.JTextField();
        deliveryAddressJTextArea = new javax.swing.JTextArea();
        priorityJComboBox = new javax.swing.JComboBox<>();
        messageJTextArea = new javax.swing.JTextArea();
        createJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Create Order Request");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 25));

        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 150, 20));
        add(productNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 350, 25));
        
        add(new javax.swing.JLabel("Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 150, 20));
        add(quantityJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 150, 25));
        
        add(new javax.swing.JLabel("Unit Price:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 75, 100, 20));
        add(unitPriceJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 75, 80, 25));
        
        add(new javax.swing.JLabel("Delivery Address:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 20));
        deliveryAddressJTextArea.setColumns(20);
        deliveryAddressJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(deliveryAddressJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 105, 350, 50));

        add(new javax.swing.JLabel("Priority:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 150, 20));
        priorityJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Low", "Medium", "High", "Urgent"}));
        add(priorityJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 165, 200, 25));

        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(4);
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 195, 350, 80));

        createJButton.setText("Create Request");
        createJButton.addActionListener(evt -> createJButtonActionPerformed());
        add(createJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> backJButtonActionPerformed());
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 100, 30));
    }

    private void createJButtonActionPerformed() {
        String productName = productNameJTextField.getText().trim();
        String quantityStr = quantityJTextField.getText().trim();
        String unitPriceStr = unitPriceJTextField.getText().trim();
        String deliveryAddress = deliveryAddressJTextArea.getText().trim();
        String message = messageJTextArea.getText().trim();

        // Validation
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
        double unitPrice;
        try {
            unitPrice = Double.parseDouble(unitPriceStr);
            if (unitPrice <= 0) {
                JOptionPane.showMessageDialog(null, "Unit price must be greater than 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid unit price");
            return;
        }
        if (deliveryAddress.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter delivery address");
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

        // Create Order Request
        OrderRequest request = new OrderRequest();
        request.setProductName(productName);
        request.setQuantity(quantity);
        request.setUnitPrice(unitPrice);
        request.setDeliveryAddress(deliveryAddress);
        request.setPriority((String) priorityJComboBox.getSelectedItem());
        request.setMessage(message.isEmpty() ? "Order request for " + productName : message);
        request.setOrderStatus("Pending");
        request.setSender(userAccount);
        request.setStatus("Sent");

        // Add to Warehouse Organization queue and user's queue
        warehouseOrg.getWorkQueue().getWorkRequestList().add(request);
        userAccount.getWorkQueue().getWorkRequestList().add(request);

        JOptionPane.showMessageDialog(null, "Order request created successfully!");
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
    private javax.swing.JTextArea deliveryAddressJTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JComboBox<String> priorityJComboBox;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField quantityJTextField;
    private javax.swing.JTextField unitPriceJTextField;
}

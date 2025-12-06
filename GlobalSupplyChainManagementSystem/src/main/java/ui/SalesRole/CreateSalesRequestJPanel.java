package ui.SalesRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.ProductionOrganization;
import Business.UserAccount.UserAccount;
import Business.Util.ValidationUtil;
import Business.WorkQueue.SalesRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateSalesRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private SupplyChainSystem business;
    private SalesRepWorkAreaJPanel parentPanel;

    public CreateSalesRequestJPanel(JPanel userProcessContainer, UserAccount account,
                                   Enterprise enterprise, SupplyChainSystem business, SalesRepWorkAreaJPanel parentPanel) {
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
        customerNameJTextField = new javax.swing.JTextField();
        customerEmailJTextField = new javax.swing.JTextField();
        productNameJTextField = new javax.swing.JTextField();
        quantityJTextField = new javax.swing.JTextField();
        unitPriceJTextField = new javax.swing.JTextField();
        paymentMethodJComboBox = new javax.swing.JComboBox<>();
        messageJTextArea = new javax.swing.JTextArea();
        createJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Create Sales Request");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 25));

        add(new javax.swing.JLabel("Customer Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 150, 20));
        add(customerNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 350, 25));
        
        add(new javax.swing.JLabel("Customer Email:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 150, 20));
        add(customerEmailJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 350, 25));
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 20));
        add(productNameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 105, 350, 25));
        
        add(new javax.swing.JLabel("Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 150, 20));
        add(quantityJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 135, 150, 25));
        
        add(new javax.swing.JLabel("Unit Price:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 135, 100, 20));
        add(unitPriceJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 135, 80, 25));

        add(new javax.swing.JLabel("Payment Method:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 150, 20));
        paymentMethodJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Credit Card", "Cash", "Net 30", "Net 60"}));
        add(paymentMethodJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 165, 200, 25));

        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(4);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 195, 350, 80));

        createJButton.setText("Create Request");
        createJButton.addActionListener(evt -> createJButtonActionPerformed());
        add(createJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> backJButtonActionPerformed());
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 100, 30));
    }

    private void createJButtonActionPerformed() {
        String customerName = customerNameJTextField.getText().trim();
        String customerEmail = customerEmailJTextField.getText().trim();
        String productName = productNameJTextField.getText().trim();
        String quantityStr = quantityJTextField.getText().trim();
        String unitPriceStr = unitPriceJTextField.getText().trim();
        String message = messageJTextArea.getText().trim();

        // Validation
        if (customerName.isEmpty() || !ValidationUtil.isValidName(customerName)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid customer name");
            return;
        }
        if (customerEmail.isEmpty() || !ValidationUtil.isValidEmail(customerEmail)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address");
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

        // Find Production Organization
        ProductionOrganization productionOrg = null;
        // Search through all networks in the ecosystem
        for (Business.Network.Network network : business.getNetworkList()) {
            for (Business.Enterprise.Enterprise ent : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (ent instanceof Business.Enterprise.ManufacturingEnterprise) {
                    for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                        if (org instanceof ProductionOrganization) {
                            productionOrg = (ProductionOrganization) org;
                            break;
                        }
                    }
                }
                if (productionOrg != null) break;
            }
            if (productionOrg != null) break;
        }

        if (productionOrg == null) {
            JOptionPane.showMessageDialog(null, "Production Organization not found");
            return;
        }

        // Create Sales Request
        SalesRequest request = new SalesRequest();
        request.setCustomerName(customerName);
        request.setCustomerEmail(customerEmail);
        request.setProductName(productName);
        request.setQuantity(quantity);
        request.setUnitPrice(unitPrice);
        request.setPaymentMethod((String) paymentMethodJComboBox.getSelectedItem());
        request.setMessage(message.isEmpty() ? "Sales request for " + productName : message);
        request.setSalesStatus("Quote");
        request.setSender(userAccount);
        request.setStatus("Sent");

        // Add to Production Organization queue and user's queue
        productionOrg.getWorkQueue().getWorkRequestList().add(request);
        userAccount.getWorkQueue().getWorkRequestList().add(request);

        JOptionPane.showMessageDialog(null, "Sales request created successfully!");
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
    private javax.swing.JTextField customerEmailJTextField;
    private javax.swing.JTextField customerNameJTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JComboBox<String> paymentMethodJComboBox;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField quantityJTextField;
    private javax.swing.JTextField unitPriceJTextField;
}


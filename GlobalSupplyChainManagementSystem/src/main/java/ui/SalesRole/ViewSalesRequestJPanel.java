package ui.SalesRole;

import Business.WorkQueue.SalesRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class ViewSalesRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SalesRequest request;
    private SalesRepWorkAreaJPanel parentPanel;

    public ViewSalesRequestJPanel(JPanel userProcessContainer, SalesRequest request, SalesRepWorkAreaJPanel parentPanel) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.request = request;
        this.parentPanel = parentPanel;
        populateFields();
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(600, 500));
        this.setMinimumSize(new java.awt.Dimension(550, 480));
    }

    private void populateFields() {
        customerNameJLabel.setText(request.getCustomerName() != null ? request.getCustomerName() : "N/A");
        customerEmailJLabel.setText(request.getCustomerEmail() != null ? request.getCustomerEmail() : "N/A");
        productNameJLabel.setText(request.getProductName() != null ? request.getProductName() : "N/A");
        quantityJLabel.setText(String.valueOf(request.getQuantity()));
        unitPriceJLabel.setText(String.format("$%.2f", request.getUnitPrice()));
        totalPriceJLabel.setText(String.format("$%.2f", request.getTotalPrice()));
        discountJLabel.setText(String.format("%.1f%%", request.getDiscount()));
        paymentMethodJLabel.setText(request.getPaymentMethod() != null ? request.getPaymentMethod() : "N/A");
        salesStatusJLabel.setText(request.getSalesStatus() != null ? request.getSalesStatus() : "N/A");
        statusJLabel.setText(request.getStatus() != null ? request.getStatus() : "N/A");
        messageJTextArea.setText(request.getMessage() != null ? request.getMessage() : "");
        messageJTextArea.setEditable(false);
        requestDateJLabel.setText(request.getRequestDate() != null ? request.getRequestDate().toString() : "N/A");
        if (request.getResolveDate() != null) {
            resolveDateJLabel.setText(request.getResolveDate().toString());
        } else {
            resolveDateJLabel.setText("N/A");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        customerNameJLabel = new javax.swing.JLabel();
        customerEmailJLabel = new javax.swing.JLabel();
        productNameJLabel = new javax.swing.JLabel();
        quantityJLabel = new javax.swing.JLabel();
        unitPriceJLabel = new javax.swing.JLabel();
        totalPriceJLabel = new javax.swing.JLabel();
        discountJLabel = new javax.swing.JLabel();
        paymentMethodJLabel = new javax.swing.JLabel();
        salesStatusJLabel = new javax.swing.JLabel();
        statusJLabel = new javax.swing.JLabel();
        messageJTextArea = new javax.swing.JTextArea();
        requestDateJLabel = new javax.swing.JLabel();
        resolveDateJLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Sales Request Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 25));

        int y = 45;
        add(new javax.swing.JLabel("Customer Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(customerNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Customer Email:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(customerEmailJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(productNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(quantityJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Unit Price:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(unitPriceJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Discount:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(discountJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Total Price:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(totalPriceJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Payment Method:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(paymentMethodJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Sales Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(salesStatusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 150, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Request Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(statusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 150, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Request Date:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(requestDateJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 300, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Resolve Date:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(resolveDateJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 300, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(3);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 60));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> {
            userProcessContainer.remove(this);
            if (parentPanel != null) {
                parentPanel.populateTable();
            }
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.previous(userProcessContainer);
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y + 70, 100, 30));
    }

    private javax.swing.JButton backJButton;
    private javax.swing.JLabel customerEmailJLabel;
    private javax.swing.JLabel customerNameJLabel;
    private javax.swing.JLabel discountJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JLabel paymentMethodJLabel;
    private javax.swing.JLabel productNameJLabel;
    private javax.swing.JLabel quantityJLabel;
    private javax.swing.JLabel requestDateJLabel;
    private javax.swing.JLabel resolveDateJLabel;
    private javax.swing.JLabel salesStatusJLabel;
    private javax.swing.JLabel statusJLabel;
    private javax.swing.JLabel totalPriceJLabel;
    private javax.swing.JLabel unitPriceJLabel;
}


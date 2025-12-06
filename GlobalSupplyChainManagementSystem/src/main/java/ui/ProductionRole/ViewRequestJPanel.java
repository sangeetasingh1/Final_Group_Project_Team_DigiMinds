package ui.ProductionRole;

import Business.WorkQueue.OrderRequest;
import Business.WorkQueue.SalesRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class ViewRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private WorkRequest request;
    private ProductionManagerWorkAreaJPanel parentPanel;

    public ViewRequestJPanel(JPanel userProcessContainer, WorkRequest request, ProductionManagerWorkAreaJPanel parentPanel) {
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
        messageJTextArea.setText(request.getMessage() != null ? request.getMessage() : "");
        messageJTextArea.setEditable(false);
        statusJLabel.setText(request.getStatus() != null ? request.getStatus() : "N/A");
        senderJLabel.setText(request.getSender() != null ? request.getSender().getEmployee().getName() : "N/A");
        receiverJLabel.setText(request.getReceiver() != null ? request.getReceiver().getEmployee().getName() : "N/A");
        requestDateJLabel.setText(request.getRequestDate() != null ? request.getRequestDate().toString() : "N/A");
        if (request.getResolveDate() != null) {
            resolveDateJLabel.setText(request.getResolveDate().toString());
        } else {
            resolveDateJLabel.setText("N/A");
        }

        // Show specific details based on request type
        if (request instanceof SalesRequest) {
            SalesRequest sr = (SalesRequest) request;
            detailsJTextArea.setText(
                "Customer: " + (sr.getCustomerName() != null ? sr.getCustomerName() : "N/A") + "\n" +
                "Email: " + (sr.getCustomerEmail() != null ? sr.getCustomerEmail() : "N/A") + "\n" +
                "Product: " + (sr.getProductName() != null ? sr.getProductName() : "N/A") + "\n" +
                "Quantity: " + sr.getQuantity() + "\n" +
                "Unit Price: $" + String.format("%.2f", sr.getUnitPrice()) + "\n" +
                "Total: $" + String.format("%.2f", sr.getTotalPrice()) + "\n" +
                "Payment Method: " + (sr.getPaymentMethod() != null ? sr.getPaymentMethod() : "N/A") + "\n" +
                "Sales Status: " + (sr.getSalesStatus() != null ? sr.getSalesStatus() : "N/A")
            );
        } else if (request instanceof OrderRequest) {
            OrderRequest or = (OrderRequest) request;
            detailsJTextArea.setText(
                "Product: " + (or.getProductName() != null ? or.getProductName() : "N/A") + "\n" +
                "Quantity: " + or.getQuantity() + "\n" +
                "Unit Price: $" + String.format("%.2f", or.getUnitPrice()) + "\n" +
                "Total: $" + String.format("%.2f", or.getTotalPrice()) + "\n" +
                "Delivery Address: " + (or.getDeliveryAddress() != null ? or.getDeliveryAddress() : "N/A") + "\n" +
                "Priority: " + (or.getPriority() != null ? or.getPriority() : "N/A") + "\n" +
                "Order Status: " + (or.getOrderStatus() != null ? or.getOrderStatus() : "N/A")
            );
        } else {
            detailsJTextArea.setText("Request Type: " + request.getClass().getSimpleName());
        }
        detailsJTextArea.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        messageJTextArea = new javax.swing.JTextArea();
        detailsJTextArea = new javax.swing.JTextArea();
        statusJLabel = new javax.swing.JLabel();
        senderJLabel = new javax.swing.JLabel();
        receiverJLabel = new javax.swing.JLabel();
        requestDateJLabel = new javax.swing.JLabel();
        resolveDateJLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Request Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 25));

        int y = 45;
        add(new javax.swing.JLabel("Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(statusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Sender:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(senderJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Receiver:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(receiverJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Request Date:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(requestDateJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 300, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Resolve Date:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(resolveDateJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 300, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 50));
        y += 60;
        
        add(new javax.swing.JLabel("Details:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        detailsJTextArea.setColumns(20);
        detailsJTextArea.setRows(6);
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane(detailsJTextArea);
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 100));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> {
            userProcessContainer.remove(this);
            if (parentPanel != null) {
                parentPanel.populateRequestTable();
            }
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.previous(userProcessContainer);
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y + 110, 100, 30));
    }

    private javax.swing.JButton backJButton;
    private javax.swing.JTextArea detailsJTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JLabel receiverJLabel;
    private javax.swing.JLabel requestDateJLabel;
    private javax.swing.JLabel resolveDateJLabel;
    private javax.swing.JLabel senderJLabel;
    private javax.swing.JLabel statusJLabel;
}


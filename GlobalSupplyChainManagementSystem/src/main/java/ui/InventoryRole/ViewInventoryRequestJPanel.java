package ui.InventoryRole;

import Business.WorkQueue.InventoryRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class ViewInventoryRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private InventoryRequest request;
    private InventoryAnalystWorkAreaJPanel parentPanel;

    public ViewInventoryRequestJPanel(JPanel userProcessContainer, InventoryRequest request, InventoryAnalystWorkAreaJPanel parentPanel) {
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
        productIdJLabel.setText(request.getProductId() != null ? request.getProductId() : "N/A");
        productNameJLabel.setText(request.getProductName() != null ? request.getProductName() : "N/A");
        requestedQuantityJLabel.setText(String.valueOf(request.getRequestedQuantity()));
        availableQuantityJLabel.setText(String.valueOf(request.getAvailableQuantity()));
        requestTypeJLabel.setText(request.getRequestType() != null ? request.getRequestType() : "N/A");
        warehouseLocationJLabel.setText(request.getWarehouseLocation() != null ? request.getWarehouseLocation() : "N/A");
        inventoryStatusJLabel.setText(request.getInventoryStatus() != null ? request.getInventoryStatus() : "N/A");
        statusJLabel.setText(request.getStatus() != null ? request.getStatus() : "N/A");
        notesJTextArea.setText(request.getNotes() != null ? request.getNotes() : "");
        notesJTextArea.setEditable(false);
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
        productIdJLabel = new javax.swing.JLabel();
        productNameJLabel = new javax.swing.JLabel();
        requestedQuantityJLabel = new javax.swing.JLabel();
        availableQuantityJLabel = new javax.swing.JLabel();
        requestTypeJLabel = new javax.swing.JLabel();
        warehouseLocationJLabel = new javax.swing.JLabel();
        inventoryStatusJLabel = new javax.swing.JLabel();
        statusJLabel = new javax.swing.JLabel();
        notesJTextArea = new javax.swing.JTextArea();
        messageJTextArea = new javax.swing.JTextArea();
        requestDateJLabel = new javax.swing.JLabel();
        resolveDateJLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Inventory Request Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 25));

        int y = 45;
        add(new javax.swing.JLabel("Product ID:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(productIdJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(productNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Requested Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(requestedQuantityJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Available Quantity:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(availableQuantityJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 100, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Request Type:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(requestTypeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Warehouse Location:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(warehouseLocationJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 25;
        
        add(new javax.swing.JLabel("Inventory Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(inventoryStatusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 150, 20));
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
        messageJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 50));
        y += 60;
        
        add(new javax.swing.JLabel("Notes:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        notesJTextArea.setColumns(20);
        notesJTextArea.setRows(3);
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane(notesJTextArea);
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 70));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> {
            userProcessContainer.remove(this);
            if (parentPanel != null) {
                parentPanel.populateTable();
            }
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.previous(userProcessContainer);
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y + 80, 100, 30));
    }

    private javax.swing.JLabel availableQuantityJLabel;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel inventoryStatusJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JTextArea notesJTextArea;
    private javax.swing.JLabel productIdJLabel;
    private javax.swing.JLabel productNameJLabel;
    private javax.swing.JLabel requestDateJLabel;
    private javax.swing.JLabel requestedQuantityJLabel;
    private javax.swing.JLabel requestTypeJLabel;
    private javax.swing.JLabel resolveDateJLabel;
    private javax.swing.JLabel statusJLabel;
    private javax.swing.JLabel warehouseLocationJLabel;
}


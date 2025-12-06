package ui.ShippingRole;

import Business.WorkQueue.ShippingRequest;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewShippingRequestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private ShippingRequest request;
    private ShippingCoordinatorWorkAreaJPanel parentPanel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public ViewShippingRequestJPanel(JPanel userProcessContainer, ShippingRequest request, ShippingCoordinatorWorkAreaJPanel parentPanel) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.request = request;
        this.parentPanel = parentPanel;
        populateFields();
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(600, 550));
        this.setMinimumSize(new java.awt.Dimension(550, 520));
    }

    private void populateFields() {
        trackingNumberJLabel.setText(request.getTrackingNumber() != null ? request.getTrackingNumber() : "N/A");
        destinationJTextArea.setText(request.getDestination() != null ? request.getDestination() : "");
        shippingMethodJComboBox.setSelectedItem(request.getShippingMethod() != null ? request.getShippingMethod() : "Standard");
        carrierJComboBox.setSelectedItem(request.getCarrier() != null ? request.getCarrier() : "FedEx");
        shippingCostJTextField.setText(request.getShippingCost() > 0 ? String.format("%.2f", request.getShippingCost()) : "");
        shippingStatusJComboBox.setSelectedItem(request.getShippingStatus() != null ? request.getShippingStatus() : "Pending");
        messageJTextArea.setText(request.getMessage() != null ? request.getMessage() : "");
        messageJTextArea.setEditable(false);
        
        if (request.getEstimatedDeliveryDate() != null) {
            estimatedDeliveryJTextField.setText(dateFormat.format(request.getEstimatedDeliveryDate()));
        }
        if (request.getActualDeliveryDate() != null) {
            actualDeliveryJTextField.setText(dateFormat.format(request.getActualDeliveryDate()));
        }
        
        statusJLabel.setText(request.getStatus() != null ? request.getStatus() : "N/A");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        trackingNumberJLabel = new javax.swing.JLabel();
        destinationJTextArea = new javax.swing.JTextArea();
        shippingMethodJComboBox = new javax.swing.JComboBox<>();
        carrierJComboBox = new javax.swing.JComboBox<>();
        shippingCostJTextField = new javax.swing.JTextField();
        shippingStatusJComboBox = new javax.swing.JComboBox<>();
        estimatedDeliveryJTextField = new javax.swing.JTextField();
        actualDeliveryJTextField = new javax.swing.JTextField();
        messageJTextArea = new javax.swing.JTextArea();
        statusJLabel = new javax.swing.JLabel();
        saveJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Shipping Request Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 25));

        int y = 45;
        add(new javax.swing.JLabel("Tracking Number:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(trackingNumberJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Destination:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        destinationJTextArea.setColumns(20);
        destinationJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(destinationJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 40));
        y += 50;
        
        add(new javax.swing.JLabel("Shipping Method:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        shippingMethodJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Standard", "Express", "Overnight", "International"}));
        add(shippingMethodJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Carrier:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        carrierJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"FedEx", "UPS", "DHL", "Custom"}));
        add(carrierJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Shipping Cost:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(shippingCostJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 150, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Shipping Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        shippingStatusJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pending", "Picked Up", "In Transit", "Out for Delivery", "Delivered", "Delayed"}));
        add(shippingStatusJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Estimated Delivery:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(estimatedDeliveryJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Actual Delivery:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(actualDeliveryJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 50));
        y += 60;
        
        add(new javax.swing.JLabel("Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(statusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));

        saveJButton.setText("Save Changes");
        saveJButton.addActionListener(evt -> {
            try {
                request.setShippingMethod((String) shippingMethodJComboBox.getSelectedItem());
                request.setCarrier((String) carrierJComboBox.getSelectedItem());
                if (!shippingCostJTextField.getText().trim().isEmpty()) {
                    request.setShippingCost(Double.parseDouble(shippingCostJTextField.getText().trim()));
                }
                request.setShippingStatus((String) shippingStatusJComboBox.getSelectedItem());
                request.setDestination(destinationJTextArea.getText().trim());
                
                if (request.getShippingStatus().equals("Delivered")) {
                    request.setActualDeliveryDate(new java.util.Date());
                    request.setStatus("Completed");
                    request.setResolveDate(new java.util.Date());
                } else {
                    request.setStatus("Processing");
                }
                
                JOptionPane.showMessageDialog(null, "Shipping information updated successfully!");
                backJButtonActionPerformed();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid shipping cost");
            }
        });
        add(saveJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y + 30, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> backJButtonActionPerformed());
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, y + 30, 100, 30));
    }

    private void backJButtonActionPerformed() {
        userProcessContainer.remove(this);
        if (parentPanel != null) {
            parentPanel.populateTable();
        }
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }

    private javax.swing.JTextField actualDeliveryJTextField;
    private javax.swing.JButton backJButton;
    private javax.swing.JComboBox<String> carrierJComboBox;
    private javax.swing.JTextArea destinationJTextArea;
    private javax.swing.JTextField estimatedDeliveryJTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JButton saveJButton;
    private javax.swing.JTextField shippingCostJTextField;
    private javax.swing.JComboBox<String> shippingMethodJComboBox;
    private javax.swing.JComboBox<String> shippingStatusJComboBox;
    private javax.swing.JLabel statusJLabel;
    private javax.swing.JLabel trackingNumberJLabel;
}


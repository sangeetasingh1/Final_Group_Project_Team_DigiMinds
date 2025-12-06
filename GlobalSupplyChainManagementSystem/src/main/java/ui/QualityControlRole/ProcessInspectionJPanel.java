package ui.QualityControlRole;

import Business.WorkQueue.QualityInspectionRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProcessInspectionJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private QualityInspectionRequest request;
    private QualityInspectorWorkAreaJPanel parentPanel;

    public ProcessInspectionJPanel(JPanel userProcessContainer, QualityInspectionRequest request, QualityInspectorWorkAreaJPanel parentPanel) {
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
        inspectionTypeJLabel.setText(request.getInspectionType() != null ? request.getInspectionType() : "N/A");
        messageJTextArea.setText(request.getMessage() != null ? request.getMessage() : "");
        messageJTextArea.setEditable(false);
        
        if (request.getInspectionResult() != null) {
            resultJComboBox.setSelectedItem(request.getInspectionResult());
        }
        if (request.getQualityScore() != null) {
            qualityScoreJComboBox.setSelectedItem(request.getQualityScore());
        }
        if (request.getDefects() != null) {
            defectsJTextArea.setText(request.getDefects());
        }
        
        statusJLabel.setText(request.getStatus() != null ? request.getStatus() : "N/A");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        productIdJLabel = new javax.swing.JLabel();
        productNameJLabel = new javax.swing.JLabel();
        inspectionTypeJLabel = new javax.swing.JLabel();
        messageJTextArea = new javax.swing.JTextArea();
        resultJComboBox = new javax.swing.JComboBox<>();
        qualityScoreJComboBox = new javax.swing.JComboBox<>();
        defectsJTextArea = new javax.swing.JTextArea();
        statusJLabel = new javax.swing.JLabel();
        saveJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Process Quality Inspection");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 25));

        int y = 45;
        add(new javax.swing.JLabel("Product ID:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(productIdJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Product Name:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(productNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Inspection Type:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(inspectionTypeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));
        y += 30;
        
        add(new javax.swing.JLabel("Message:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        messageJTextArea.setColumns(20);
        messageJTextArea.setRows(2);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane(messageJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 50));
        y += 60;
        
        add(new javax.swing.JLabel("Inspection Result:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        resultJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pass", "Fail", "Conditional"}));
        add(resultJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Quality Score:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        qualityScoreJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"A", "B", "C", "D", "F"}));
        add(qualityScoreJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 200, 25));
        y += 30;
        
        add(new javax.swing.JLabel("Defects/Notes:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        defectsJTextArea.setColumns(20);
        defectsJTextArea.setRows(3);
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane(defectsJTextArea);
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 70));
        y += 80;
        
        add(new javax.swing.JLabel("Status:"), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 150, 20));
        add(statusJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, y, 350, 20));

        saveJButton.setText("Save & Complete");
        saveJButton.addActionListener(evt -> {
            request.setInspectionResult((String) resultJComboBox.getSelectedItem());
            request.setQualityScore((String) qualityScoreJComboBox.getSelectedItem());
            request.setDefects(defectsJTextArea.getText().trim());
            request.setApproved(true);
            request.setReceiver(userAccount);
            request.setStatus("Completed");
            request.setResolveDate(new java.util.Date());
            JOptionPane.showMessageDialog(null, "Inspection completed successfully!");
            backJButtonActionPerformed();
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

    private javax.swing.JButton backJButton;
    private javax.swing.JTextArea defectsJTextArea;
    private javax.swing.JLabel inspectionTypeJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea messageJTextArea;
    private javax.swing.JLabel productIdJLabel;
    private javax.swing.JLabel productNameJLabel;
    private javax.swing.JComboBox<String> qualityScoreJComboBox;
    private javax.swing.JComboBox<String> resultJComboBox;
    private javax.swing.JButton saveJButton;
    private javax.swing.JLabel statusJLabel;
}

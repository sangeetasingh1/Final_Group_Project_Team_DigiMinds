package ui.ProductionRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.ProductionOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.OrderRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ProductionManagerWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private ProductionOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private SupplyChainSystem business;

    public ProductionManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, 
                                           ProductionOrganization organization, Enterprise enterprise, SupplyChainSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business = business;
        enterpriseLabel.setText("Enterprise: " + enterprise.getName());
        populateRequestTable();
    }

    public void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        model.setRowCount(0);

        // Show requests from organization queue
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            String requestType = request.getClass().getSimpleName().replace("Request", "");
            Object[] row = new Object[6];
            row[0] = request;
            row[1] = requestType;
            row[2] = request.getSender() != null ? request.getSender().getEmployee().getName() : "N/A";
            row[3] = request.getMessage() != null ? (request.getMessage().length() > 50 ? request.getMessage().substring(0, 50) + "..." : request.getMessage()) : "N/A";
            row[4] = request.getStatus();
            row[5] = request.getRequestDate();
            model.addRow(row);
        }
        
        // Also show requests from user's personal queue
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()) {
            if (!organization.getWorkQueue().getWorkRequestList().contains(request)) {
                String requestType = request.getClass().getSimpleName().replace("Request", "");
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = requestType;
                row[2] = request.getSender() != null ? request.getSender().getEmployee().getName() : "N/A";
                row[3] = request.getMessage() != null ? (request.getMessage().length() > 50 ? request.getMessage().substring(0, 50) + "..." : request.getMessage()) : "N/A";
                row[4] = request.getStatus();
                row[5] = request.getRequestDate();
                model.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        createOrderJButton = new javax.swing.JButton();
        processRequestJButton = new javax.swing.JButton();
        refreshJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Request", "Type", "Sender", "Message", "Status", "Date"}
        ) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.Object.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 860, 220));

        createOrderJButton.setText("Create Order");
        createOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderJButtonActionPerformed(evt);
            }
        });
        add(createOrderJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, 30));
        
        createQualityInspectionJButton = new javax.swing.JButton();
        createQualityInspectionJButton.setText("Create Inspection");
        createQualityInspectionJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createQualityInspectionJButtonActionPerformed(evt);
            }
        });
        add(createQualityInspectionJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 140, 30));

        processRequestJButton.setText("View Details");
        processRequestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processRequestJButtonActionPerformed(evt);
            }
        });
        add(processRequestJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 140, 30));

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 100, 30));
        
        // Set panel size to ensure all components are visible
        this.setPreferredSize(new java.awt.Dimension(900, 350));
        this.setMinimumSize(new java.awt.Dimension(700, 350));

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        enterpriseLabel.setText("Enterprise: ");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));
    }

    private void createOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("CreateOrderPanel", 
            new CreateOrderRequestJPanel(userProcessContainer, userAccount, enterprise, business, this));
        layout.next(userProcessContainer);
    }

    private void processRequestJButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = workRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request to view");
            return;
        }
        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("ViewRequestPanel",
            new ViewRequestJPanel(userProcessContainer, request, this));
        layout.next(userProcessContainer);
    }

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {
        populateRequestTable();
    }

    private void createQualityInspectionJButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("CreateQualityInspectionPanel",
            new CreateQualityInspectionJPanel(userProcessContainer, userAccount, enterprise, business, this));
        layout.next(userProcessContainer);
    }

    private javax.swing.JButton createOrderJButton;
    private javax.swing.JButton createQualityInspectionJButton;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton processRequestJButton;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
}


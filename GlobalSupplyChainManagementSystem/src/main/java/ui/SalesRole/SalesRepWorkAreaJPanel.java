package ui.SalesRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.SalesOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.SalesRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class SalesRepWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SalesOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private SupplyChainSystem business;

    public SalesRepWorkAreaJPanel(JPanel userProcessContainer, UserAccount account,
                                  SalesOrganization organization, Enterprise enterprise, SupplyChainSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business = business;
        enterpriseLabel.setText("Enterprise: " + enterprise.getName());
        populateTable();
    }

    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        model.setRowCount(0);
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof SalesRequest) {
                SalesRequest sr = (SalesRequest) request;
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = sr.getCustomerName();
                row[2] = sr.getProductName();
                row[3] = sr.getQuantity();
                row[4] = sr.getSalesStatus();
                model.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        createSalesJButton = new javax.swing.JButton();
        processJButton = new javax.swing.JButton();
        refreshJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Request", "Customer", "Product", "Quantity", "Status"}
        ) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.String.class,
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 860, 220));

        createSalesJButton.setText("Create Sales Request");
        createSalesJButton.addActionListener(evt -> {
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            userProcessContainer.add("CreateSalesPanel",
                new CreateSalesRequestJPanel(userProcessContainer, userAccount, enterprise, business, this));
            layout.next(userProcessContainer);
        });
        add(createSalesJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, 30));

        processJButton.setText("View Details");
        processJButton.addActionListener(evt -> {
            int selectedRow = workRequestJTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Please select a request");
                return;
            }
            SalesRequest request = (SalesRequest) workRequestJTable.getValueAt(selectedRow, 0);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            userProcessContainer.add("ViewSalesPanel",
                new ViewSalesRequestJPanel(userProcessContainer, request, this));
            layout.next(userProcessContainer);
        });
        add(processJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 140, 30));
        
        updateStatusJButton = new javax.swing.JButton();
        updateStatusJButton.setText("Update Status");
        updateStatusJButton.addActionListener(evt -> {
            int selectedRow = workRequestJTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Please select a request");
                return;
            }
            SalesRequest request = (SalesRequest) workRequestJTable.getValueAt(selectedRow, 0);
            String[] statuses = {"Quote", "Negotiating", "Confirmed", "Fulfilled", "Cancelled"};
            String currentStatus = request.getSalesStatus() != null ? request.getSalesStatus() : "Quote";
            String newStatus = (String) JOptionPane.showInputDialog(null,
                "Select new status:", "Update Status",
                JOptionPane.QUESTION_MESSAGE, null, statuses, currentStatus);
            if (newStatus != null && !newStatus.equals(currentStatus)) {
                request.setSalesStatus(newStatus);
                if (newStatus.equals("Confirmed") || newStatus.equals("Fulfilled")) {
                    request.setStatus("Processing");
                }
                if (newStatus.equals("Fulfilled")) {
                    request.setResolveDate(new java.util.Date());
                    request.setStatus("Completed");
                }
                JOptionPane.showMessageDialog(null, "Status updated to: " + newStatus);
                populateTable();
            }
        });
        add(updateStatusJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 140, 30));

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(evt -> populateTable());
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 100, 30));
        
        // Set panel size to ensure all components are visible
        this.setPreferredSize(new java.awt.Dimension(900, 350));
        this.setMinimumSize(new java.awt.Dimension(700, 350));

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));
    }

    private javax.swing.JButton createSalesJButton;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton processJButton;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JButton updateStatusJButton;
    private javax.swing.JTable workRequestJTable;
}


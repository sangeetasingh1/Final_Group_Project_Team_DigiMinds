package ui.ShippingRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.ShippingOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ShippingRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ShippingCoordinatorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private ShippingOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private SupplyChainSystem business;

    public ShippingCoordinatorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account,
                                            ShippingOrganization organization, Enterprise enterprise, SupplyChainSystem business) {
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
            if (request instanceof ShippingRequest) {
                ShippingRequest sr = (ShippingRequest) request;
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = sr.getTrackingNumber();
                row[2] = sr.getDestination();
                row[3] = sr.getShippingStatus();
                row[4] = request.getStatus();
                model.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        processJButton = new javax.swing.JButton();
        refreshJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Request", "Tracking #", "Destination", "Shipping Status", "Status"}
        ) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class};
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

        processJButton.setText("View/Update Details");
        processJButton.addActionListener(evt -> {
            int selectedRow = workRequestJTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Please select a request");
                return;
            }
            ShippingRequest request = (ShippingRequest) workRequestJTable.getValueAt(selectedRow, 0);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            userProcessContainer.add("ViewShippingPanel",
                new ViewShippingRequestJPanel(userProcessContainer, request, this));
            layout.next(userProcessContainer);
        });
        add(processJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, 30));

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(evt -> populateTable());
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 100, 30));

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));
        
        // Set panel size to ensure all components are visible
        this.setPreferredSize(new java.awt.Dimension(900, 350));
        this.setMinimumSize(new java.awt.Dimension(700, 350));
    }

    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton processJButton;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
}


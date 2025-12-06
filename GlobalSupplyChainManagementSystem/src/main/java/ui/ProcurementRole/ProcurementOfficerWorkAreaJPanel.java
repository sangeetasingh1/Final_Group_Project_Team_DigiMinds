package ui.ProcurementRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ProcurementOfficerWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private SupplyChainSystem business;

    public ProcurementOfficerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account,
                                            Organization organization, Enterprise enterprise, SupplyChainSystem business) {
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
            Object[] row = new Object[3];
            row[0] = request;
            row[1] = request.getMessage();
            row[2] = request.getStatus();
            model.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        refreshJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Request", "Message", "Status"}
        ) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{false, false, false};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 800, 200));

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(evt -> populateTable());
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, -1));

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));
    }

    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
}


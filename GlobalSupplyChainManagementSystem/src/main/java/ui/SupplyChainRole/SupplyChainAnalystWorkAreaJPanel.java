package ui.SupplyChainRole;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Reporting.SystemReport;
import Business.UserAccount.UserAccount;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SupplyChainAnalystWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Organization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private SupplyChainSystem business;

    public SupplyChainAnalystWorkAreaJPanel(JPanel userProcessContainer, UserAccount account,
                                           Organization organization, Enterprise enterprise, SupplyChainSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business = business;
        enterpriseLabel.setText("Enterprise: " + enterprise.getName());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        enterpriseLabel = new javax.swing.JLabel();
        viewReportJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportJTextArea = new javax.swing.JTextArea();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        enterpriseLabel.setText("Enterprise: ");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));

        viewReportJButton.setText("Generate System Report");
        viewReportJButton.addActionListener(evt -> {
            SystemReport report = new SystemReport(business);
            String reportText = report.generateReportSummary();
            reportJTextArea.setText(reportText);
        });
        add(viewReportJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 200, -1));

        reportJTextArea.setColumns(20);
        reportJTextArea.setRows(5);
        reportJTextArea.setEditable(false);
        jScrollPane1.setViewportView(reportJTextArea);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 900, 400));
    }

    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea reportJTextArea;
    private javax.swing.JButton viewReportJButton;
}


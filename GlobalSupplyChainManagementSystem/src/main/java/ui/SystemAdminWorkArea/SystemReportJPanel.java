package ui.SystemAdminWorkArea;

import Business.SupplyChainSystem;
import Business.Reporting.ReportData;
import Business.Reporting.SystemReport;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * 
 * @author sange
 */
public class SystemReportJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SupplyChainSystem system;
    
    // Chart panels
    private BarChartPanel enterpriseChart;
    private BarChartPanel organizationChart;
    private BarChartPanel requestTypeChart;
    private PieChartPanel requestStatusChart;
    private PieChartPanel enterpriseDistributionChart;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JLabel summaryLabel;
    private javax.swing.JPanel chartsPanel;

    public SystemReportJPanel(JPanel userProcessContainer, SupplyChainSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        generateReport();
    }

    private void generateReport() {
        if (system == null) {
            return;
        }
        
        try {
            SystemReport reportGenerator = new SystemReport(system);
            ReportData report = reportGenerator.generateReport();
            
            if (report == null) {
                return;
            }
            
            // Update summary panel
            updateSummaryPanel(report);
            
            // Update charts - handle null maps
            if (enterpriseChart != null) {
                enterpriseChart.setData(report.getEnterpriseTypeCount());
            }
            if (organizationChart != null) {
                organizationChart.setData(report.getOrganizationTypeCount());
            }
            if (requestTypeChart != null) {
                requestTypeChart.setData(report.getRequestTypeCount());
            }
            if (requestStatusChart != null) {
                requestStatusChart.setData(report.getRequestStatusCount());
            }
            if (enterpriseDistributionChart != null) {
                enterpriseDistributionChart.setData(report.getEnterpriseTypeCount());
            }
        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void updateSummaryPanel(ReportData report) {
        if (report == null || summaryLabel == null) {
            return;
        }
        
        try {
            StringBuilder summary = new StringBuilder();
            summary.append("<html><body style='font-family: Tahoma; font-size: 12px; padding: 10px;'>");
            summary.append("<h2 style='color: #4682B4; margin-top: 0;'>System Overview</h2>");
            
            summary.append("<table style='width: 100%; border-collapse: collapse;'>");
            summary.append("<tr style='background-color: #F0F0F0;'><td style='padding: 8px; font-weight: bold;'>Total Networks:</td><td style='padding: 8px;'>").append(report.getTotalNetworks()).append("</td></tr>");
            summary.append("<tr><td style='padding: 8px; font-weight: bold;'>Total Enterprises:</td><td style='padding: 8px;'>").append(report.getTotalEnterprises()).append("</td></tr>");
            summary.append("<tr style='background-color: #F0F0F0;'><td style='padding: 8px; font-weight: bold;'>Total Organizations:</td><td style='padding: 8px;'>").append(report.getTotalOrganizations()).append("</td></tr>");
            summary.append("<tr><td style='padding: 8px; font-weight: bold;'>Total Employees:</td><td style='padding: 8px;'>").append(report.getTotalEmployees()).append("</td></tr>");
            summary.append("<tr style='background-color: #F0F0F0;'><td style='padding: 8px; font-weight: bold;'>Total User Accounts:</td><td style='padding: 8px;'>").append(report.getTotalUserAccounts()).append("</td></tr>");
            summary.append("<tr><td style='padding: 8px; font-weight: bold;'>Total Work Requests:</td><td style='padding: 8px;'>").append(report.getTotalWorkRequests()).append("</td></tr>");
            summary.append("</table>");
            
            summary.append("<h3 style='color: #4682B4; margin-top: 20px;'>Performance Metrics</h3>");
            summary.append("<table style='width: 100%; border-collapse: collapse;'>");
            summary.append("<tr style='background-color: #F0F0F0;'><td style='padding: 8px; font-weight: bold;'>Avg Organizations per Enterprise:</td><td style='padding: 8px;'>").append(String.format("%.2f", report.getAvgOrganizationsPerEnterprise())).append("</td></tr>");
            summary.append("<tr><td style='padding: 8px; font-weight: bold;'>Avg Employees per Organization:</td><td style='padding: 8px;'>").append(String.format("%.2f", report.getAvgEmployeesPerOrganization())).append("</td></tr>");
            summary.append("<tr style='background-color: #F0F0F0;'><td style='padding: 8px; font-weight: bold;'>Avg Users per Organization:</td><td style='padding: 8px;'>").append(String.format("%.2f", report.getAvgUsersPerOrganization())).append("</td></tr>");
            summary.append("</table>");
            
            summary.append("</body></html>");
            summaryLabel.setText(summary.toString());
        } catch (Exception e) {
            System.err.println("Error updating summary panel: " + e.getMessage());
            e.printStackTrace();
            summaryLabel.setText("<html><body>Error loading report data.</body></html>");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        refreshJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        summaryPanel = new javax.swing.JPanel();
        summaryLabel = new javax.swing.JLabel();
        chartsPanel = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setBackground(Color.WHITE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("System Report - Global Supply Chain Management System");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 600, 35));

        refreshJButton.setText("Refresh Report");
        refreshJButton.addActionListener(evt -> generateReport());
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 140, 30));

        backJButton.setText("Back");
        backJButton.addActionListener(evt -> {
            userProcessContainer.remove(this);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.previous(userProcessContainer);
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 650, 100, 30));

        // Summary Panel
        summaryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        summaryPanel.setBackground(Color.WHITE);
        summaryLabel.setText("<html>Loading...</html>");
        summaryLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        JScrollPane summaryScroll = new JScrollPane(summaryLabel);
        summaryScroll.setBorder(null);
        summaryPanel.add(summaryScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1000, 580));
        jTabbedPane1.addTab("Summary", summaryPanel);

        // Charts Panel with multiple charts
        chartsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        chartsPanel.setBackground(Color.WHITE);
        
        // Enterprise Distribution Pie Chart
        enterpriseDistributionChart = new PieChartPanel("Enterprise Distribution");
        enterpriseDistributionChart.setPreferredSize(new java.awt.Dimension(500, 350));
        chartsPanel.add(enterpriseDistributionChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 500, 350));
        
        // Request Status Pie Chart
        requestStatusChart = new PieChartPanel("Work Request Status Distribution");
        requestStatusChart.setPreferredSize(new java.awt.Dimension(500, 350));
        chartsPanel.add(requestStatusChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 500, 350));
        
        // Enterprise Type Bar Chart
        enterpriseChart = new BarChartPanel("Enterprise Types", "Enterprise Type", "Count");
        enterpriseChart.setPreferredSize(new java.awt.Dimension(500, 350));
        chartsPanel.add(enterpriseChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 500, 350));
        
        // Organization Type Bar Chart
        organizationChart = new BarChartPanel("Organization Types", "Organization Type", "Count");
        organizationChart.setPreferredSize(new java.awt.Dimension(500, 350));
        chartsPanel.add(organizationChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, 500, 350));
        
        // Request Type Bar Chart
        requestTypeChart = new BarChartPanel("Work Request Types", "Request Type", "Count");
        requestTypeChart.setPreferredSize(new java.awt.Dimension(1000, 350));
        chartsPanel.add(requestTypeChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 790, 1000, 350));
        
        JScrollPane chartsScroll = new JScrollPane(chartsPanel);
        chartsScroll.setBorder(null);
        chartsScroll.setPreferredSize(new java.awt.Dimension(1040, 600));
        jTabbedPane1.addTab("Charts", chartsScroll);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 55, 1040, 580));
        
        // Set panel size
        this.setPreferredSize(new java.awt.Dimension(1100, 700));
        this.setMinimumSize(new java.awt.Dimension(1000, 650));
    }

    private javax.swing.JButton backJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton refreshJButton;
}

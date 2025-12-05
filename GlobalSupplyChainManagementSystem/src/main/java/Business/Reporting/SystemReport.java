package Business.Reporting;

import Business.SupplyChainSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import Business.WorkQueue.OrderRequest;
import Business.WorkQueue.QualityInspectionRequest;
import Business.WorkQueue.ShippingRequest;
import Business.WorkQueue.InventoryRequest;
import Business.WorkQueue.SalesRequest;
import Business.WorkQueue.ProcurementRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sange
 */
public class SystemReport {
    
    private SupplyChainSystem system;
    
    public SystemReport(SupplyChainSystem system) {
        this.system = system;
    }
    
    public ReportData generateReport() {
        ReportData report = new ReportData();
        
        // Null check for system
        if (system == null || system.getNetworkList() == null) {
            return report;
        }
        
        // Network level statistics
        report.setTotalNetworks(system.getNetworkList().size());
        
        int totalEnterprises = 0;
        int totalOrganizations = 0;
        int totalEmployees = 0;
        int totalUserAccounts = 0;
        int totalWorkRequests = 0;
        
        Map<String, Integer> requestTypeCount = new HashMap<>();
        Map<String, Integer> requestStatusCount = new HashMap<>();
        Map<String, Integer> enterpriseTypeCount = new HashMap<>();
        Map<String, Integer> organizationTypeCount = new HashMap<>();
        
        for (Network network : system.getNetworkList()) {
            if (network == null || network.getEnterpriseDirectory() == null || 
                network.getEnterpriseDirectory().getEnterpriseList() == null) {
                continue;
            }
            
            totalEnterprises += network.getEnterpriseDirectory().getEnterpriseList().size();
            
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise == null) continue;
                
                String enterpriseType = "Unknown";
                if (enterprise.getEnterpriseType() != null) {
                    enterpriseType = enterprise.getEnterpriseType().getValue();
                    if (enterpriseType == null) enterpriseType = "Unknown";
                }
                enterpriseTypeCount.put(enterpriseType, 
                    enterpriseTypeCount.getOrDefault(enterpriseType, 0) + 1);
                
                if (enterprise.getOrganizationDirectory() == null || 
                    enterprise.getOrganizationDirectory().getOrganizationList() == null) {
                    continue;
                }
                
                totalOrganizations += enterprise.getOrganizationDirectory().getOrganizationList().size();
                
                for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (org == null) continue;
                    
                    String orgType = org.getName();
                    if (orgType == null) orgType = "Unknown";
                    organizationTypeCount.put(orgType, 
                        organizationTypeCount.getOrDefault(orgType, 0) + 1);
                    
                    if (org.getEmployeeDirectory() != null && org.getEmployeeDirectory().getEmployeeList() != null) {
                        totalEmployees += org.getEmployeeDirectory().getEmployeeList().size();
                    }
                    if (org.getUserAccountDirectory() != null && org.getUserAccountDirectory().getUserAccountList() != null) {
                        totalUserAccounts += org.getUserAccountDirectory().getUserAccountList().size();
                        
                        // Count user work queues
                        for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                            if (ua != null && ua.getWorkQueue() != null && ua.getWorkQueue().getWorkRequestList() != null) {
                                totalWorkRequests += ua.getWorkQueue().getWorkRequestList().size();
                            }
                        }
                    }
                    
                    // Count work requests
                    WorkQueue workQueue = org.getWorkQueue();
                    if (workQueue != null && workQueue.getWorkRequestList() != null) {
                        for (WorkRequest wr : workQueue.getWorkRequestList()) {
                            if (wr == null) continue;
                            
                            totalWorkRequests++;
                            
                            // Count by type
                            String requestType = wr.getClass().getSimpleName();
                            if (requestType == null) requestType = "Unknown";
                            requestTypeCount.put(requestType, 
                                requestTypeCount.getOrDefault(requestType, 0) + 1);
                            
                            // Count by status
                            String status = wr.getStatus();
                            if (status != null) {
                                requestStatusCount.put(status, 
                                    requestStatusCount.getOrDefault(status, 0) + 1);
                            }
                        }
                    }
                }
            }
        }
        
        report.setTotalEnterprises(totalEnterprises);
        report.setTotalOrganizations(totalOrganizations);
        report.setTotalEmployees(totalEmployees);
        report.setTotalUserAccounts(totalUserAccounts);
        report.setTotalWorkRequests(totalWorkRequests);
        report.setRequestTypeCount(requestTypeCount);
        report.setRequestStatusCount(requestStatusCount);
        report.setEnterpriseTypeCount(enterpriseTypeCount);
        report.setOrganizationTypeCount(organizationTypeCount);
        
        // Calculate averages
        if (totalEnterprises > 0) {
            report.setAvgOrganizationsPerEnterprise((double) totalOrganizations / totalEnterprises);
        }
        if (totalOrganizations > 0) {
            report.setAvgEmployeesPerOrganization((double) totalEmployees / totalOrganizations);
            report.setAvgUsersPerOrganization((double) totalUserAccounts / totalOrganizations);
        }
        
        return report;
    }
    
    public String generateReportSummary() {
        ReportData report = generateReport();
        StringBuilder summary = new StringBuilder();
        
        summary.append("=== SYSTEM REPORT ===\n\n");
        summary.append("NETWORK STATISTICS:\n");
        summary.append("Total Networks: ").append(report.getTotalNetworks()).append("\n");
        summary.append("Total Enterprises: ").append(report.getTotalEnterprises()).append("\n");
        summary.append("Total Organizations: ").append(report.getTotalOrganizations()).append("\n\n");
        
        summary.append("USER STATISTICS:\n");
        summary.append("Total Employees: ").append(report.getTotalEmployees()).append("\n");
        summary.append("Total User Accounts: ").append(report.getTotalUserAccounts()).append("\n");
        summary.append("Average Employees per Organization: ").append(String.format("%.2f", report.getAvgEmployeesPerOrganization())).append("\n");
        summary.append("Average Users per Organization: ").append(String.format("%.2f", report.getAvgUsersPerOrganization())).append("\n\n");
        
        summary.append("WORK REQUEST STATISTICS:\n");
        summary.append("Total Work Requests: ").append(report.getTotalWorkRequests()).append("\n\n");
        
        summary.append("REQUEST TYPES:\n");
        for (Map.Entry<String, Integer> entry : report.getRequestTypeCount().entrySet()) {
            summary.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        summary.append("\n");
        
        summary.append("REQUEST STATUS:\n");
        for (Map.Entry<String, Integer> entry : report.getRequestStatusCount().entrySet()) {
            summary.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        summary.append("\n");
        
        summary.append("ENTERPRISE DISTRIBUTION:\n");
        for (Map.Entry<String, Integer> entry : report.getEnterpriseTypeCount().entrySet()) {
            summary.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        summary.append("\n");
        
        summary.append("ORGANIZATION DISTRIBUTION:\n");
        for (Map.Entry<String, Integer> entry : report.getOrganizationTypeCount().entrySet()) {
            summary.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        return summary.toString();
    }
}


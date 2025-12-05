package Business.Reporting;

import java.util.Map;

/**
 * 
 * @author sange
 */
public class ReportData {
    private int totalNetworks;
    private int totalEnterprises;
    private int totalOrganizations;
    private int totalEmployees;
    private int totalUserAccounts;
    private int totalWorkRequests;
    private double avgOrganizationsPerEnterprise;
    private double avgEmployeesPerOrganization;
    private double avgUsersPerOrganization;
    private Map<String, Integer> requestTypeCount;
    private Map<String, Integer> requestStatusCount;
    private Map<String, Integer> enterpriseTypeCount;
    private Map<String, Integer> organizationTypeCount;
    
    // Getters and Setters
    public int getTotalNetworks() {
        return totalNetworks;
    }
    
    public void setTotalNetworks(int totalNetworks) {
        this.totalNetworks = totalNetworks;
    }
    
    public int getTotalEnterprises() {
        return totalEnterprises;
    }
    
    public void setTotalEnterprises(int totalEnterprises) {
        this.totalEnterprises = totalEnterprises;
    }
    
    public int getTotalOrganizations() {
        return totalOrganizations;
    }
    
    public void setTotalOrganizations(int totalOrganizations) {
        this.totalOrganizations = totalOrganizations;
    }
    
    public int getTotalEmployees() {
        return totalEmployees;
    }
    
    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
    
    public int getTotalUserAccounts() {
        return totalUserAccounts;
    }
    
    public void setTotalUserAccounts(int totalUserAccounts) {
        this.totalUserAccounts = totalUserAccounts;
    }
    
    public int getTotalWorkRequests() {
        return totalWorkRequests;
    }
    
    public void setTotalWorkRequests(int totalWorkRequests) {
        this.totalWorkRequests = totalWorkRequests;
    }
    
    public double getAvgOrganizationsPerEnterprise() {
        return avgOrganizationsPerEnterprise;
    }
    
    public void setAvgOrganizationsPerEnterprise(double avgOrganizationsPerEnterprise) {
        this.avgOrganizationsPerEnterprise = avgOrganizationsPerEnterprise;
    }
    
    public double getAvgEmployeesPerOrganization() {
        return avgEmployeesPerOrganization;
    }
    
    public void setAvgEmployeesPerOrganization(double avgEmployeesPerOrganization) {
        this.avgEmployeesPerOrganization = avgEmployeesPerOrganization;
    }
    
    public double getAvgUsersPerOrganization() {
        return avgUsersPerOrganization;
    }
    
    public void setAvgUsersPerOrganization(double avgUsersPerOrganization) {
        this.avgUsersPerOrganization = avgUsersPerOrganization;
    }
    
    public Map<String, Integer> getRequestTypeCount() {
        return requestTypeCount;
    }
    
    public void setRequestTypeCount(Map<String, Integer> requestTypeCount) {
        this.requestTypeCount = requestTypeCount;
    }
    
    public Map<String, Integer> getRequestStatusCount() {
        return requestStatusCount;
    }
    
    public void setRequestStatusCount(Map<String, Integer> requestStatusCount) {
        this.requestStatusCount = requestStatusCount;
    }
    
    public Map<String, Integer> getEnterpriseTypeCount() {
        return enterpriseTypeCount;
    }
    
    public void setEnterpriseTypeCount(Map<String, Integer> enterpriseTypeCount) {
        this.enterpriseTypeCount = enterpriseTypeCount;
    }
    
    public Map<String, Integer> getOrganizationTypeCount() {
        return organizationTypeCount;
    }
    
    public void setOrganizationTypeCount(Map<String, Integer> organizationTypeCount) {
        this.organizationTypeCount = organizationTypeCount;
    }
}


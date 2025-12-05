package Business;

import Business.DataGenerator.DataGenerator;
import Business.Employee.Employee;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import Business.Util.PasswordUtil;

/**
 * 
 * @author sange
 * 
 */
public class ConfigureASystem {
    
    public static SupplyChainSystem configure(){
        
        SupplyChainSystem system = SupplyChainSystem.getInstance();
        
        // Create system admin
        Employee employee = system.getEmployeeDirectory().createEmployee("System Administrator");
        employee.setEmail("admin@ecosystem.com");
        employee.setAge(35);
        employee.setPhoneNumber("555-0001");
        
        String hashedPassword = PasswordUtil.hashPassword("admin123");
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("sysadmin", hashedPassword, employee, new SystemAdminRole());
        
        // Populate system with test data using Faker
        DataGenerator.populateSystem(system);
        
        return system;
    }
    
}

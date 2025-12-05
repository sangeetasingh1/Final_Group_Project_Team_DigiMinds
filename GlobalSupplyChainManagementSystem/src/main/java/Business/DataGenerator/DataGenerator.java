package Business.DataGenerator;

import Business.SupplyChainSystem;
import Business.Employee.Employee;
import Business.Employee.EmployeeDirectory;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ManufacturingEnterprise;
import Business.Enterprise.LogisticsEnterprise;
import Business.Enterprise.RetailEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.Organization.ProductionOrganization;
import Business.Organization.QualityControlOrganization;
import Business.Organization.WarehouseOrganization;
import Business.Organization.ShippingOrganization;
import Business.Organization.InventoryOrganization;
import Business.Organization.SalesOrganization;
import Business.Role.ProductionManagerRole;
import Business.Role.QualityInspectorRole;
import Business.Role.WarehouseManagerRole;
import Business.Role.ShippingCoordinatorRole;
import Business.Role.InventoryAnalystRole;
import Business.Role.SalesRepRole;
import Business.Role.ProcurementOfficerRole;
import Business.Role.SupplyChainAnalystRole;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import Business.Util.PasswordUtil;
import Business.WorkQueue.OrderRequest;
import Business.WorkQueue.QualityInspectionRequest;
import Business.WorkQueue.ShippingRequest;
import Business.WorkQueue.InventoryRequest;
import Business.WorkQueue.ProcurementRequest;
import Business.WorkQueue.SalesRequest;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author sange
 * 
 */
public class DataGenerator {
    
    private static Faker faker = new Faker();
    private static Random random = new Random();
    
    public static void populateSystem(SupplyChainSystem system) {
        // Create network
        Network network = system.createAndAddNetwork();
        network.setName("Global Supply Chain Network");
        
        // Create 3 enterprises
        EnterpriseDirectory enterpriseDir = network.getEnterpriseDirectory();
        
        // Manufacturing Enterprise
        ManufacturingEnterprise manufacturing = (ManufacturingEnterprise) 
            enterpriseDir.createAndAddEnterprise("Global Manufacturing Corp", 
                Enterprise.EnterpriseType.Manufacturing);
        
        // Logistics Enterprise
        LogisticsEnterprise logistics = (LogisticsEnterprise) 
            enterpriseDir.createAndAddEnterprise("Worldwide Logistics Inc", 
                Enterprise.EnterpriseType.Logistics);
        
        // Retail Enterprise
        RetailEnterprise retail = (RetailEnterprise) 
            enterpriseDir.createAndAddEnterprise("International Retail Group", 
                Enterprise.EnterpriseType.Retail);
        
        // Create organizations for Manufacturing Enterprise
        OrganizationDirectory mfgOrgDir = manufacturing.getOrganizationDirectory();
        ProductionOrganization production = (ProductionOrganization) 
            mfgOrgDir.createOrganization(Organization.Type.Production);
        QualityControlOrganization quality = (QualityControlOrganization) 
            mfgOrgDir.createOrganization(Organization.Type.QualityControl);
        
        // Create organizations for Logistics Enterprise
        OrganizationDirectory logOrgDir = logistics.getOrganizationDirectory();
        WarehouseOrganization warehouse = (WarehouseOrganization) 
            logOrgDir.createOrganization(Organization.Type.Warehouse);
        ShippingOrganization shipping = (ShippingOrganization) 
            logOrgDir.createOrganization(Organization.Type.Shipping);
        
        // Create organizations for Retail Enterprise
        OrganizationDirectory retailOrgDir = retail.getOrganizationDirectory();
        InventoryOrganization inventory = (InventoryOrganization) 
            retailOrgDir.createOrganization(Organization.Type.Inventory);
        SalesOrganization sales = (SalesOrganization) 
            retailOrgDir.createOrganization(Organization.Type.Sales);
        
        // Populate employees and user accounts for each organization
        populateOrganization(production, new ProductionManagerRole(), "Production Manager", 3);
        populateOrganization(quality, new QualityInspectorRole(), "Quality Inspector", 4);
        populateOrganization(warehouse, new WarehouseManagerRole(), "Warehouse Manager", 3);
        populateOrganization(shipping, new ShippingCoordinatorRole(), "Shipping Coordinator", 4);
        populateOrganization(inventory, new InventoryAnalystRole(), "Inventory Analyst", 3);
        populateOrganization(sales, new SalesRepRole(), "Sales Representative", 5);
        
        // Create work requests for use case scenarios
        createUseCaseScenarios(production, quality, warehouse, shipping, inventory, sales);
        
        // Create additional work requests for variety
        createWorkRequests(production, quality, warehouse, shipping, inventory, sales);
    }
    
    private static void populateOrganization(Organization org, Business.Role.Role role, 
                                           String roleName, int count) {
        EmployeeDirectory empDir = org.getEmployeeDirectory();
        UserAccountDirectory userDir = org.getUserAccountDirectory();
        
        for (int i = 0; i < count; i++) {
            Employee emp = empDir.createEmployee(generateEmployeeName());
            emp.setEmail(generateEmail());
            emp.setAge(generateAge());
            emp.setPhoneNumber(generatePhoneNumber());
            
            String username = generateUsername(emp.getName());
            String password = "Password123"; // Default password for demo
            String hashedPassword = PasswordUtil.hashPassword(password);
            
            UserAccount userAccount = userDir.createUserAccount(username, hashedPassword, emp, role);
        }
    }
    
    /**
     * Creates pre-populated data for the 5 use case scenarios from the presentation
     */
    private static void createUseCaseScenarios(ProductionOrganization production,
                                              QualityControlOrganization quality,
                                              WarehouseOrganization warehouse,
                                              ShippingOrganization shipping,
                                              InventoryOrganization inventory,
                                              SalesOrganization sales) {
        
        // Get user accounts for each organization
        UserAccount salesRep = sales.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : sales.getUserAccountDirectory().getUserAccountList().get(0);
        UserAccount prodManager = production.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : production.getUserAccountDirectory().getUserAccountList().get(0);
        UserAccount qualityInspector = quality.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : quality.getUserAccountDirectory().getUserAccountList().get(0);
        UserAccount warehouseManager = warehouse.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : warehouse.getUserAccountDirectory().getUserAccountList().get(0);
        UserAccount shippingCoord = shipping.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : shipping.getUserAccountDirectory().getUserAccountList().get(0);
        UserAccount inventoryAnalyst = inventory.getUserAccountDirectory().getUserAccountList().isEmpty() 
            ? null : inventory.getUserAccountDirectory().getUserAccountList().get(0);
        
        // ============================================================
        // USE CASE 1: Order Fulfillment Workflow
        // ============================================================
        // Step 1: Sales Rep creates Sales Request
        SalesRequest salesReq1 = new SalesRequest();
        salesReq1.setCustomerName("John Smith");
        salesReq1.setCustomerEmail("john.smith@email.com");
        salesReq1.setProductName("Premium Widget Set");
        salesReq1.setQuantity(50);
        salesReq1.setUnitPrice(29.99);
        salesReq1.setPaymentMethod("Credit Card");
        salesReq1.setSalesStatus("Confirmed");
        salesReq1.setMessage("Customer order for Premium Widget Set - 50 units");
        salesReq1.setStatus("Sent");
        salesReq1.setRequestDate(new Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000L)); // 5 days ago
        if (salesRep != null) {
            salesReq1.setSender(salesRep);
            salesRep.getWorkQueue().getWorkRequestList().add(salesReq1);
        }
        production.getWorkQueue().getWorkRequestList().add(salesReq1);
        
        // Step 2: Production Manager creates Order Request to Warehouse
        OrderRequest orderReq1 = new OrderRequest();
        orderReq1.setProductName("Premium Widget Set");
        orderReq1.setQuantity(50);
        orderReq1.setUnitPrice(29.99);
        orderReq1.setDeliveryAddress("123 Main Street, New York, NY 10001");
        orderReq1.setPriority("High");
        orderReq1.setOrderStatus("Confirmed");
        orderReq1.setMessage("Order request for Premium Widget Set - 50 units for customer John Smith");
        orderReq1.setStatus("Sent");
        orderReq1.setRequestDate(new Date(System.currentTimeMillis() - 4 * 24 * 60 * 60 * 1000L)); // 4 days ago
        if (prodManager != null) {
            orderReq1.setSender(prodManager);
            prodManager.getWorkQueue().getWorkRequestList().add(orderReq1);
        }
        warehouse.getWorkQueue().getWorkRequestList().add(orderReq1);
        
        // Step 3: Quality Inspector performs quality check
        QualityInspectionRequest qualityReq1 = new QualityInspectionRequest();
        qualityReq1.setProductId("PROD-WIDGET-001");
        qualityReq1.setProductName("Premium Widget Set");
        qualityReq1.setInspectionType("Final");
        qualityReq1.setInspectionResult("Pass");
        qualityReq1.setQualityScore("A");
        qualityReq1.setMessage("Final quality inspection for Premium Widget Set batch");
        qualityReq1.setStatus("Completed");
        qualityReq1.setRequestDate(new Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000L)); // 3 days ago
        qualityReq1.setResolveDate(new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000L)); // 2 days ago
        if (prodManager != null) {
            qualityReq1.setSender(prodManager);
        }
        if (qualityInspector != null) {
            qualityReq1.setReceiver(qualityInspector);
            qualityInspector.getWorkQueue().getWorkRequestList().add(qualityReq1);
        }
        quality.getWorkQueue().getWorkRequestList().add(qualityReq1);
        
        // Step 4: Shipping Coordinator arranges shipment
        ShippingRequest shipReq1 = new ShippingRequest();
        shipReq1.setTrackingNumber("TRK-2024-001234");
        shipReq1.setDestination("123 Main Street, New York, NY 10001");
        shipReq1.setShippingMethod("Express");
        shipReq1.setCarrier("FedEx");
        shipReq1.setShippingCost(45.50);
        shipReq1.setShippingStatus("In Transit");
        shipReq1.setMessage("Express shipment for Premium Widget Set - Order #001234");
        shipReq1.setStatus("Processing");
        shipReq1.setRequestDate(new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000L)); // 2 days ago
        shipReq1.setEstimatedDeliveryDate(new Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000L)); // Tomorrow
        if (warehouseManager != null) {
            shipReq1.setSender(warehouseManager);
        }
        if (shippingCoord != null) {
            shipReq1.setReceiver(shippingCoord);
            shippingCoord.getWorkQueue().getWorkRequestList().add(shipReq1);
        }
        shipping.getWorkQueue().getWorkRequestList().add(shipReq1);
        
        // ============================================================
        // USE CASE 2: Quality Inspection Workflow
        // ============================================================
        QualityInspectionRequest qualityReq2 = new QualityInspectionRequest();
        qualityReq2.setProductId("PROD-BATCH-2024-045");
        qualityReq2.setInspectionType("Final");
        qualityReq2.setMessage("Quality inspection required for production batch 2024-045");
        qualityReq2.setStatus("Pending");
        qualityReq2.setRequestDate(new Date(System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000L)); // 1 day ago
        if (prodManager != null) {
            qualityReq2.setSender(prodManager);
            prodManager.getWorkQueue().getWorkRequestList().add(qualityReq2);
        }
        quality.getWorkQueue().getWorkRequestList().add(qualityReq2);
        
        // Another quality inspection that's been completed
        QualityInspectionRequest qualityReq3 = new QualityInspectionRequest();
        qualityReq3.setProductId("PROD-BATCH-2024-044");
        qualityReq3.setInspectionType("In-Process");
        qualityReq3.setInspectionResult("Pass");
        qualityReq3.setQualityScore("B");
        qualityReq3.setDefects("Minor surface imperfections, within tolerance");
        qualityReq3.setMessage("In-process inspection for batch 2024-044");
        qualityReq3.setStatus("Completed");
        qualityReq3.setRequestDate(new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000L)); // 7 days ago
        qualityReq3.setResolveDate(new Date(System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000L)); // 6 days ago
        if (prodManager != null) {
            qualityReq3.setSender(prodManager);
        }
        if (qualityInspector != null) {
            qualityReq3.setReceiver(qualityInspector);
            qualityReq3.setApprovedBy(qualityInspector);
            qualityReq3.setApproved(true);
        }
        quality.getWorkQueue().getWorkRequestList().add(qualityReq3);
        
        // ============================================================
        // USE CASE 3: Cross-Enterprise Inventory Management
        // ============================================================
        InventoryRequest invReq1 = new InventoryRequest();
        invReq1.setProductId("PROD-INV-789");
        invReq1.setProductName("Standard Component Kit");
        invReq1.setRequestedQuantity(150);
        invReq1.setAvailableQuantity(75);
        invReq1.setRequestType("Stock Check");
        invReq1.setWarehouseLocation("Warehouse A - Section 3");
        invReq1.setMessage("Stock check required for Standard Component Kit - Low stock detected");
        invReq1.setInventoryStatus("Pending");
        invReq1.setStatus("Sent");
        invReq1.setRequestDate(new Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000L)); // 3 days ago
        if (inventoryAnalyst != null) {
            invReq1.setSender(inventoryAnalyst);
            inventoryAnalyst.getWorkQueue().getWorkRequestList().add(invReq1);
        }
        warehouse.getWorkQueue().getWorkRequestList().add(invReq1);
        
        // Another inventory request - Restock
        InventoryRequest invReq2 = new InventoryRequest();
        invReq2.setProductId("PROD-INV-456");
        invReq2.setProductName("Premium Component Kit");
        invReq2.setRequestedQuantity(200);
        invReq2.setAvailableQuantity(25);
        invReq2.setRequestType("Restock");
        invReq2.setWarehouseLocation("Warehouse B - Section 1");
        invReq2.setMessage("Restock request for Premium Component Kit - Critical low stock");
        invReq2.setInventoryStatus("Approved");
        invReq2.setStatus("Processing");
        invReq2.setRequestDate(new Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000L)); // 5 days ago
        if (inventoryAnalyst != null) {
            invReq2.setSender(inventoryAnalyst);
        }
        if (warehouseManager != null) {
            invReq2.setReceiver(warehouseManager);
        }
        warehouse.getWorkQueue().getWorkRequestList().add(invReq2);
        
        // ============================================================
        // USE CASE 4: Shipping Coordination
        // ============================================================
        ShippingRequest shipReq2 = new ShippingRequest();
        shipReq2.setTrackingNumber("TRK-2024-002567");
        shipReq2.setDestination("456 Commerce Blvd, Los Angeles, CA 90001");
        shipReq2.setShippingMethod("Standard");
        shipReq2.setCarrier("UPS");
        shipReq2.setShippingCost(28.75);
        shipReq2.setShippingStatus("Picked Up");
        shipReq2.setMessage("Standard shipment for Standard Component Kit - Order #002567");
        shipReq2.setStatus("Pending");
        shipReq2.setRequestDate(new Date(System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000L)); // 1 day ago
        if (warehouseManager != null) {
            shipReq2.setSender(warehouseManager);
        }
        shipping.getWorkQueue().getWorkRequestList().add(shipReq2);
        
        // Another shipping request - Delivered
        ShippingRequest shipReq3 = new ShippingRequest();
        shipReq3.setTrackingNumber("TRK-2024-001890");
        shipReq3.setDestination("789 Market Street, Chicago, IL 60601");
        shipReq3.setShippingMethod("Overnight");
        shipReq3.setCarrier("DHL");
        shipReq3.setShippingCost(65.00);
        shipReq3.setShippingStatus("Delivered");
        shipReq3.setMessage("Overnight shipment for Premium Component Kit - Order #001890");
        shipReq3.setStatus("Completed");
        shipReq3.setRequestDate(new Date(System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000L)); // 10 days ago
        shipReq3.setEstimatedDeliveryDate(new Date(System.currentTimeMillis() - 9 * 24 * 60 * 60 * 1000L)); // 9 days ago
        shipReq3.setActualDeliveryDate(new Date(System.currentTimeMillis() - 9 * 24 * 60 * 60 * 1000L)); // 9 days ago
        shipReq3.setResolveDate(new Date(System.currentTimeMillis() - 9 * 24 * 60 * 60 * 1000L)); // 9 days ago
        if (warehouseManager != null) {
            shipReq3.setSender(warehouseManager);
        }
        if (shippingCoord != null) {
            shipReq3.setReceiver(shippingCoord);
        }
        shipping.getWorkQueue().getWorkRequestList().add(shipReq3);
        
        // ============================================================
        // USE CASE 5: Additional Sales Orders for Reporting
        // ============================================================
        // Create multiple sales requests in different states
        SalesRequest salesReq2 = new SalesRequest();
        salesReq2.setCustomerName("Sarah Johnson");
        salesReq2.setCustomerEmail("sarah.j@email.com");
        salesReq2.setProductName("Deluxe Widget Set");
        salesReq2.setQuantity(25);
        salesReq2.setUnitPrice(49.99);
        salesReq2.setPaymentMethod("Net 30");
        salesReq2.setSalesStatus("Negotiating");
        salesReq2.setMessage("Quote request for Deluxe Widget Set - 25 units");
        salesReq2.setStatus("Sent");
        salesReq2.setRequestDate(new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000L)); // 2 days ago
        if (salesRep != null) {
            salesReq2.setSender(salesRep);
            salesRep.getWorkQueue().getWorkRequestList().add(salesReq2);
        }
        production.getWorkQueue().getWorkRequestList().add(salesReq2);
        
        SalesRequest salesReq3 = new SalesRequest();
        salesReq3.setCustomerName("Michael Brown");
        salesReq3.setCustomerEmail("m.brown@email.com");
        salesReq3.setProductName("Basic Widget Set");
        salesReq3.setQuantity(100);
        salesReq3.setUnitPrice(19.99);
        salesReq3.setDiscount(10.0);
        salesReq3.setPaymentMethod("Credit Card");
        salesReq3.setSalesStatus("Fulfilled");
        salesReq3.setMessage("Completed order for Basic Widget Set - 100 units");
        salesReq3.setStatus("Completed");
        salesReq3.setRequestDate(new Date(System.currentTimeMillis() - 15 * 24 * 60 * 60 * 1000L)); // 15 days ago
        salesReq3.setResolveDate(new Date(System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000L)); // 10 days ago
        if (salesRep != null) {
            salesReq3.setSender(salesRep);
        }
        production.getWorkQueue().getWorkRequestList().add(salesReq3);
    }
    
    private static void createWorkRequests(ProductionOrganization production,
                                         QualityControlOrganization quality,
                                         WarehouseOrganization warehouse,
                                         ShippingOrganization shipping,
                                         InventoryOrganization inventory,
                                         SalesOrganization sales) {
        
        // Cross-organization requests (within same enterprise)
        // Production -> Quality Control
        for (int i = 0; i < 2; i++) {
            QualityInspectionRequest qr = new QualityInspectionRequest();
            qr.setMessage("Quality inspection required for batch " + (i + 1));
            qr.setProductId("PROD-" + faker.number().digits(6));
            qr.setInspectionType("Final");
            qr.setStatus("Sent");
            if (!production.getUserAccountDirectory().getUserAccountList().isEmpty()) {
                qr.setSender(production.getUserAccountDirectory().getUserAccountList().get(0));
            }
            quality.getWorkQueue().getWorkRequestList().add(qr);
        }
        
        // Warehouse -> Shipping (cross-organization within Logistics)
        for (int i = 0; i < 2; i++) {
            ShippingRequest sr = new ShippingRequest();
            sr.setMessage("Shipment ready for dispatch");
            sr.setDestination(faker.address().city() + ", " + faker.address().country());
            sr.setShippingMethod("Express");
            sr.setCarrier("DHL");
            sr.setStatus("Sent");
            if (!warehouse.getUserAccountDirectory().getUserAccountList().isEmpty()) {
                sr.setSender(warehouse.getUserAccountDirectory().getUserAccountList().get(0));
            }
            shipping.getWorkQueue().getWorkRequestList().add(sr);
        }
        
        // Cross-enterprise requests
        // Manufacturing -> Logistics (Order Request)
        for (int i = 0; i < 2; i++) {
            OrderRequest or = new OrderRequest();
            or.setMessage("New order for production");
            or.setProductName(faker.commerce().productName());
            or.setQuantity(random.nextInt(100) + 10);
            or.setUnitPrice(Double.parseDouble(faker.commerce().price()));
            or.setDeliveryAddress(faker.address().fullAddress());
            or.setStatus("Sent");
            if (!production.getUserAccountDirectory().getUserAccountList().isEmpty()) {
                or.setSender(production.getUserAccountDirectory().getUserAccountList().get(0));
            }
            warehouse.getWorkQueue().getWorkRequestList().add(or);
        }
        
        // Retail -> Manufacturing (Sales Request)
        for (int i = 0; i < 2; i++) {
            SalesRequest salesReq = new SalesRequest();
            salesReq.setMessage("New sales order");
            salesReq.setCustomerName(faker.name().fullName());
            salesReq.setCustomerEmail(faker.internet().emailAddress());
            salesReq.setProductName(faker.commerce().productName());
            salesReq.setQuantity(random.nextInt(50) + 5);
            salesReq.setUnitPrice(Double.parseDouble(faker.commerce().price()));
            salesReq.setStatus("Sent");
            if (!sales.getUserAccountDirectory().getUserAccountList().isEmpty()) {
                salesReq.setSender(sales.getUserAccountDirectory().getUserAccountList().get(0));
            }
            production.getWorkQueue().getWorkRequestList().add(salesReq);
        }
        
        // Inventory requests (cross-enterprise)
        InventoryRequest ir = new InventoryRequest();
        ir.setMessage("Stock check required");
        ir.setProductId("PROD-" + faker.number().digits(6));
        ir.setProductName(faker.commerce().productName());
        ir.setRequestedQuantity(random.nextInt(100) + 20);
        ir.setRequestType("Stock Check");
        ir.setStatus("Sent");
        if (!inventory.getUserAccountDirectory().getUserAccountList().isEmpty()) {
            ir.setSender(inventory.getUserAccountDirectory().getUserAccountList().get(0));
        }
        warehouse.getWorkQueue().getWorkRequestList().add(ir);
    }
    
    private static String generateEmployeeName() {
        return faker.name().fullName();
    }
    
    private static String generateEmail() {
        return faker.internet().emailAddress();
    }
    
    private static int generateAge() {
        return random.nextInt(43) + 22; // Age between 22-65
    }
    
    private static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    
    private static String generateUsername(String name) {
        String username = name.toLowerCase().replaceAll("[^a-z0-9]", "");
        if (username.length() > 15) {
            username = username.substring(0, 15);
        }
        return username + random.nextInt(1000);
    }
}


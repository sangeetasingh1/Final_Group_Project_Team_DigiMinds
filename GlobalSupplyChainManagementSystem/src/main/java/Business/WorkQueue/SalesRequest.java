package Business.WorkQueue;

import Business.UserAccount.UserAccount;

public class SalesRequest extends WorkRequest {
    
    private String customerName;
    private String customerEmail;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double discount;
    private String salesStatus; // "Quote", "Negotiating", "Confirmed", "Fulfilled", "Cancelled"
    private String paymentMethod; // "Cash", "Credit Card", "Net 30", "Net 60"
    private String salesNotes;
    
    public SalesRequest() {
        super();
        this.salesStatus = "Quote";
        this.discount = 0.0;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSalesNotes() {
        return salesNotes;
    }

    public void setSalesNotes(String salesNotes) {
        this.salesNotes = salesNotes;
    }
    
    public double getTotalPrice() {
        double total = quantity * unitPrice;
        return total - (total * discount / 100);
    }
}


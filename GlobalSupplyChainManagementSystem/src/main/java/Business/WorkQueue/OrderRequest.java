package Business.WorkQueue;

import Business.UserAccount.UserAccount;

public class OrderRequest extends WorkRequest {
    
    private String productName;
    private int quantity;
    private double unitPrice;
    private String deliveryAddress;
    private String orderStatus; // "Pending", "Confirmed", "In Production", "Shipped", "Delivered", "Cancelled"
    private String priority; // "Low", "Medium", "High", "Urgent"
    
    public OrderRequest() {
        super();
        this.orderStatus = "Pending";
        this.priority = "Medium";
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}


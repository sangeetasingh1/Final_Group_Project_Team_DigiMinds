package Business.WorkQueue;

import Business.UserAccount.UserAccount;
import java.util.Date;

public class ShippingRequest extends WorkRequest {
    
    private String trackingNumber;
    private String shippingMethod; // "Standard", "Express", "Overnight", "International"
    private String destination;
    private double shippingCost;
    private Date estimatedDeliveryDate;
    private Date actualDeliveryDate;
    private String carrier; // "FedEx", "UPS", "DHL", "Custom"
    private String shippingStatus; // "Pending", "Picked Up", "In Transit", "Out for Delivery", "Delivered", "Delayed"
    
    public ShippingRequest() {
        super();
        this.shippingStatus = "Pending";
        this.trackingNumber = generateTrackingNumber();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
    
    private String generateTrackingNumber() {
        return "TRK" + System.currentTimeMillis() % 1000000;
    }
}


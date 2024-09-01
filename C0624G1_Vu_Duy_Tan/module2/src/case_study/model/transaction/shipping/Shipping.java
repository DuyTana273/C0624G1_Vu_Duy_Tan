package case_study.model.transaction.shipping;

import java.util.Date;

public class Shipping {
    private int shippingId;
    private int orderId;
    private String address;
    private ShippingStatus status;
    private Date shippedDate;
    private Date estimatedDeliveryDate;

    public Shipping(int shippingId, int orderId, String address, Date estimatedDeliveryDate) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.address = address;
        this.status = ShippingStatus.PENDING;
        this.shippedDate = null;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public int getShippingId() {
        return shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getAddress() {
        return address;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
        if (status == ShippingStatus.SHIPPED) {
            this.shippedDate = new Date();
        }
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "shippingId=" + shippingId +
                ", orderId=" + orderId +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", shippedDate=" + shippedDate +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                '}';
    }
}


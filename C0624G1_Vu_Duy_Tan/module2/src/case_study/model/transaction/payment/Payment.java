package case_study.model.transaction.payment;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int orderId;
    private double amount;
    private Date paymentDate;
    private PaymentMethod method;
    private boolean isSuccessful;

    public Payment(int paymentId, int orderId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = new Date();
        this.method = method;
        this.isSuccessful = false;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", method=" + method +
                ", isSuccessful=" + isSuccessful +
                '}';
    }
}
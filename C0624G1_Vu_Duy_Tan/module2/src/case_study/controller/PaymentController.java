package case_study.controller;

import case_study.model.transaction.payment.Payment;
import case_study.model.transaction.payment.PaymentMethod;
import case_study.view.PaymentView;

public class PaymentController {
    private Payment payment;
    private PaymentView paymentView;

    public PaymentController(PaymentView paymentView) {
        this.paymentView = paymentView;
    }

    public void processPayment(int orderId, double amount, PaymentMethod method) {
        this.payment = new Payment(orderId, orderId, amount, method);
        payment.setSuccessful(true); // Giả sử thanh toán thành công
        paymentView.showMessage("Thanh toán thành công cho đơn hàng ID: " + orderId);
    }
}

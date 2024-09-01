package case_study.controller;

import case_study.model.transaction.shipping.Shipping;
import case_study.model.transaction.shipping.ShippingStatus;
import case_study.view.ShippingView;

public class ShippingController {
    private Shipping shipping;
    private final ShippingView shippingView;

    public ShippingController(ShippingView shippingView) {
        this.shippingView = shippingView;
    }

    public void createShipping(int orderId, String address) {
        this.shipping = new Shipping(orderId, orderId, address, null);
        shippingView.showMessage("Đã tạo thông tin vận chuyển cho đơn hàng ID: " + orderId);
    }

    public void updateShippingStatus(ShippingStatus status) {
        if (shipping != null) {
            shipping.setStatus(status);
            shippingView.showMessage("Trạng thái vận chuyển đã được cập nhật: " + status);
        }
    }
}
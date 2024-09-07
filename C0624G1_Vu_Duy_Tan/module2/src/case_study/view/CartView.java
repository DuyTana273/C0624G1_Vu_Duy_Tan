package case_study.view;

import java.util.ArrayList;
import java.util.List;

public class CartView {

    //====== ĐỊNH NGHĨA CÁC THUỘC TÍNH ======
    private UserView userView;

    //====== CONSTRUCTOR =======
    public CartView(UserView userView) {
        this.userView = userView;
    }

    //========= MENU CART =========
    public void showCartMenu() {
        List<String> options = new ArrayList<>();
        options.add("Hiển thị giỏ hàng");
        options.add("Thêm sản phẩm vào giỏ hàng");
        options.add("Xóa sản phẩm khỏi giỏ hàng");
        options.add("Xóa toàn bộ giỏ hàng");
        options.add("Quay lại Menu trước");
        userView.showMenu("QUẢN LÝ GIỎ HÀNG CÁ NHÂN", options);
    }
}

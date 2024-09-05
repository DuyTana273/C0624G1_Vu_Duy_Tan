package case_study.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartView {

    //====== ĐỊNH NGHĨA CÁC THUỘC TÍNH ======
    private UserView userView;
    private final Scanner scanner = new Scanner(System.in);

    //====== CONSTRUCTOR =======
    public CartView(UserView userView) {
        this.userView = userView;
    }

    //========= MENU CART =========
    public void showCartMenu() {
        List<String> options = new ArrayList<>();
        options.add("Hiển thị giỏ hàng");
        options.add("Thêm sản phẩm vào giỏ hàng");
        options.add("Cập nhật số lượng sản phẩm");
        options.add("Xóa sản phẩm khỏi giỏ hàng");
        options.add("Tính tiền giỏ hàng");
        options.add("Xóa toàn bộ giỏ hàng");
        options.add("Quay lại");
        userView.showMenu("QUẢN LÝ GIỎ HÀNG CÁ NHÂN", options);
    }
}

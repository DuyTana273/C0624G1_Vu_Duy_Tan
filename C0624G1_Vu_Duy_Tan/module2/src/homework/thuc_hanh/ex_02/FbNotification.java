package homework.thuc_hanh.ex_02;

public class FbNotification implements Notification{

    @Override
    public void notifyUser(String message) {
        System.out.println("Gửi Fb: " + message);
    }
}

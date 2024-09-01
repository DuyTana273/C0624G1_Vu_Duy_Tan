package homework.thuc_hanh.ex_02;

public class EmailNotification implements Notification {

    @Override
    public void notifyUser(String message) {
        System.out.println("Gửi Email: " + message);
    }
}

package homework.thuc_hanh.ex_02;

public class Main {

    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();

        Notification notification = notificationFactory.createNotification("SMS");
        notification.notifyUser("Đây là thông báo SMS.");

        notification = notificationFactory.createNotification("Email");
        notification.notifyUser("Đây là thông báo Email.");

        notification = notificationFactory.createNotification("Fb");
        notification.notifyUser("Đây là thông báo Fb.");
    }
}

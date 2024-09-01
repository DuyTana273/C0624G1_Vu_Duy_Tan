package homework.thuc_hanh.ex_02;

public class NotificationFactory {

    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "SMS":
                return new SMSNotification();
            case "Email":
                return new EmailNotification();
            case "Fb":
                return new FbNotification();
            default:
                throw new IllegalArgumentException("Loại thông báo không hợp lệ: " + channel);
        }
    }
}

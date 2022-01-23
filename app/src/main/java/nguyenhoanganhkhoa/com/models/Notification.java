package nguyenhoanganhkhoa.com.models;

public class Notification {
    private int notificationThumb;
    private String notificationContent;
    private String notificationDate;

    public int getNotificationThumb() {
        return notificationThumb;
    }

    public void setNotificationThumb(int notificationThumb) {
        this.notificationThumb = notificationThumb;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Notification(int notificationThumb, String notificationContent, String notificationDate) {
        this.notificationThumb = notificationThumb;
        this.notificationContent = notificationContent;
        this.notificationDate = notificationDate;
    }
}

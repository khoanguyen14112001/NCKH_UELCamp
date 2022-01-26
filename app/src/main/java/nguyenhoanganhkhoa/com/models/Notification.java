package nguyenhoanganhkhoa.com.models;

public class Notification {
    private String notificationType;
    private String notificationContent;
    private String notificationDate;
    private boolean isNewNotification;
    private boolean isFriendRequest = false;
    private String gender;

    public Notification(String notificationType, String notificationContent, String notificationDate, boolean isNewNotification, boolean isFriendRequest, String gender) {
        this.notificationType = notificationType;
        this.notificationContent = notificationContent;
        this.notificationDate = notificationDate;
        this.isNewNotification = isNewNotification;
        this.isFriendRequest = isFriendRequest;
        this.gender = gender;
    }

    public Notification(String notificationType, String notificationContent, String notificationDate, boolean isNewNotification) {
        this.notificationType = notificationType;
        this.notificationContent = notificationContent;
        this.notificationDate = notificationDate;
        this.isNewNotification = isNewNotification;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
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

    public boolean isNewNotification() {
        return isNewNotification;
    }

    public void setNewNotification(boolean newNotification) {
        isNewNotification = newNotification;
    }

    public boolean isFriendRequest() {
        return isFriendRequest;
    }

    public void setFriendRequest(boolean friendRequest) {
        isFriendRequest = friendRequest;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

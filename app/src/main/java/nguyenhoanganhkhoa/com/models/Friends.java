package nguyenhoanganhkhoa.com.models;

public class Friends {
    private boolean isMaleFriend;
    private String friendName;
    private String friendPhone;
    private boolean isFavoriteFriend;

    public boolean isMaleFriend() {
        return isMaleFriend;
    }

    public void setMaleFriend(boolean maleFriend) {
        isMaleFriend = maleFriend;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendPhone() {
        return friendPhone;
    }

    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }

    public boolean isFavoriteFriend() {
        return isFavoriteFriend;
    }

    public void setFavoriteFriend(boolean favoriteFriend) {
        isFavoriteFriend = favoriteFriend;
    }

    public Friends(boolean isMaleFriend, String friendName, String friendPhone, boolean isFavoriteFriend) {
        this.isMaleFriend = isMaleFriend;
        this.friendName = friendName;
        this.friendPhone = friendPhone;
        this.isFavoriteFriend = isFavoriteFriend;

    }
}

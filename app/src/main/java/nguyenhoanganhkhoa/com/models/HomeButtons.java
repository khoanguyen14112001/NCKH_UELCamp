package nguyenhoanganhkhoa.com.models;

public class HomeButtons {
    private int thumbButton;
    private String nameButton;
    private int statusButton;

    public int getThumbButton() {
        return thumbButton;
    }

    public void setThumbButton(int thumbButton) {
        this.thumbButton = thumbButton;
    }

    public String getNameButton() {
        return nameButton;
    }

    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }

    public int getStatusButton() {
        return statusButton;
    }

    public void setStatusButton(int statusButton) {
        this.statusButton = statusButton;
    }

    public HomeButtons(int thumbButton, String nameButton) {
        this.thumbButton = thumbButton;
        this.nameButton = nameButton;
    }

    public HomeButtons(int thumbButton, String nameButton, int statusButton) {
        this.thumbButton = thumbButton;
        this.nameButton = nameButton;
        this.statusButton = statusButton;
    }
}

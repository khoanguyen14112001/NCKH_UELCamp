package nguyenhoanganhkhoa.com.models;

public class PurchaseStatus {
    private String nameStatus;
    private int quantity;
    private boolean isChosen = false;

    public PurchaseStatus(String nameStatus, int quantity, boolean isChosen) {
        this.nameStatus = nameStatus;
        this.quantity = quantity;
        this.isChosen = isChosen;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getQuantityPurchase_toString(){
        return "(" + quantity + ")";
     }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PurchaseStatus(String nameStatus, int quantity) {
        this.nameStatus = nameStatus;
        this.quantity = quantity;
    }
}

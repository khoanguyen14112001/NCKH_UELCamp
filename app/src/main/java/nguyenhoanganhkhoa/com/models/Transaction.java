package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String statusTrans;
    private String dateTrans;
    private String moneyTrans;
    private int imgStatusTrans;
    private int imgSuccessTrans;

    public int getImgSuccessTrans() {
        return imgSuccessTrans;
    }

    public void setImgSuccessTrans(int imgSuccessTrans) {
        this.imgSuccessTrans = imgSuccessTrans;
    }

    public String getStatusTrans() {
        return statusTrans;
    }

    public void setStatusTrans(String statusTrans) {
        this.statusTrans = statusTrans;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public String getMoneyTrans() {
        return moneyTrans;
    }

    public void setMoneyTrans(String moneyTrans) {
        this.moneyTrans = moneyTrans;
    }

    public int getImgStatusTrans() {
        return imgStatusTrans;
    }

    public void setImgStatusTrans(int imgStatusTrans) {
        this.imgStatusTrans = imgStatusTrans;
    }

    public Transaction(String statusTrans, String dateTrans, String moneyTrans, int imgStatusTrans, int imgSuccessTrans) {
        this.statusTrans = statusTrans;
        this.dateTrans = dateTrans;
        this.moneyTrans = moneyTrans;
        this.imgStatusTrans = imgStatusTrans;
        this.imgSuccessTrans = imgSuccessTrans;
    }
}

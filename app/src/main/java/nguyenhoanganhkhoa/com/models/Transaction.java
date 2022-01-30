package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String typeTrans;
    private boolean isSuccess = true;
    private String dateTrans;
    private boolean isIncome;
    private double amountTrans;

    public String getTypeTrans() {
        return typeTrans;
    }

    public void setTypeTrans(String typeTrans) {
        this.typeTrans = typeTrans;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public double getAmountTrans() {
        return amountTrans;
    }

    public void setAmountTrans(double amountTrans) {
        this.amountTrans = amountTrans;
    }

    public Transaction(String typeTrans, String dateTrans, boolean isIncome, double amountTrans) {
        this.typeTrans = typeTrans;
        this.dateTrans = dateTrans;
        this.isIncome = isIncome;
        this.amountTrans = amountTrans;
    }

    public Transaction(String typeTrans, String dateTrans, boolean isIncome, double amountTrans, boolean isSuccess) {
        this.typeTrans = typeTrans;
        this.dateTrans = dateTrans;
        this.isIncome = isIncome;
        this.amountTrans = amountTrans;
        this.isSuccess = isSuccess;

    }
}

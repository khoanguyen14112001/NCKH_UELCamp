package nguyenhoanganhkhoa.com.models;

import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class Voucher {
    ReusedConstraint reusedConstraint = new ReusedConstraint();
    private String voucherName;
    private String voucherContent;
    private String voucherContent2;
    private double voucherUpTo;
    private String voucherExpireDate;
    private boolean isActive = true;

    public Voucher(String voucherName, String voucherContent, String voucherContent2, double voucherUpTo, String voucherExpireDate) {
        this.voucherName = voucherName;
        this.voucherContent = voucherContent;
        this.voucherContent2 = voucherContent2;
        this.voucherUpTo = voucherUpTo;
        this.voucherExpireDate = voucherExpireDate;
    }

    public Voucher(String voucherName, String voucherContent, String voucherContent2, double voucherUpTo, String voucherExpireDate, boolean isActive) {
        this.voucherName = voucherName;
        this.voucherContent = voucherContent;
        this.voucherContent2 = voucherContent2;
        this.voucherUpTo = voucherUpTo;
        this.voucherExpireDate = voucherExpireDate;
        this.isActive = isActive;
    }

    public String getVoucherUpTo_String(){
        return "Up to " + reusedConstraint.formatCurrency(getVoucherUpTo()) + "k discount";
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherContent() {
        return voucherContent;
    }

    public void setVoucherContent(String voucherContent) {
        this.voucherContent = voucherContent;
    }

    public String getVoucherContent2() {
        return voucherContent2;
    }

    public void setVoucherContent2(String voucherContent2) {
        this.voucherContent2 = voucherContent2;
    }

    public double getVoucherUpTo() {
        return voucherUpTo;
    }

    public void setVoucherUpTo(double voucherUpTo) {
        this.voucherUpTo = voucherUpTo;
    }

    public String getVoucherExpireDate() {
        return "Expire date: " + voucherExpireDate;
    }

    public void setVoucherExpireDate(String voucherExpireDate) {
        this.voucherExpireDate = voucherExpireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setVoucherStatus(boolean voucherStatus) {
        this.isActive = voucherStatus;
    }
}

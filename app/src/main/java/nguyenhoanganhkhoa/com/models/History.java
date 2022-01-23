package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

public class History implements Serializable {
    private int colorHis;
    private String statusInOut;
    private String dayInOut;

    public int getColorHis() {
        return colorHis;
    }

    public void setColorHis(int colorHis) {
        this.colorHis = colorHis;
    }

    public String getStatusInOut() {
        return statusInOut;
    }

    public void setStatusInOut(String statusInOut) {
        this.statusInOut = statusInOut;
    }

    public String getDayInOut() {
        return dayInOut;
    }

    public void setDayInOut(String dayInOut) {
        this.dayInOut = dayInOut;
    }

    public History(int colorHis, String statusInOut, String dayInOut) {
        this.colorHis = colorHis;
        this.statusInOut = statusInOut;
        this.dayInOut = dayInOut;
    }
}

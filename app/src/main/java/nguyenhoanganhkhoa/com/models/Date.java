package nguyenhoanganhkhoa.com.models;

import java.util.List;

public class Date {
    private String dateParking;
    private List<History> histories;

    public String getDateParking() {
        return dateParking;
    }

    public void setDateParking(String dateParking) {
        this.dateParking = dateParking;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public Date(String dateParking, List<History> histories) {
        this.dateParking = dateParking;
        this.histories = histories;
    }
}

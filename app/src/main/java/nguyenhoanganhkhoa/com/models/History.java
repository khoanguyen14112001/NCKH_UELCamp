package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

public class History implements Serializable {
    private String dateHistory;
    private boolean isEntry;

    public String getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(String dateHistory) {
        this.dateHistory = dateHistory;
    }

    public boolean isEntry() {
        return isEntry;
    }

    public void setEntry(boolean entry) {
        isEntry = entry;
    }

    public History(String dateHistory, boolean isEntry) {
        this.dateHistory = dateHistory;
        this.isEntry = isEntry;
    }
}

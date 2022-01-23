
package nguyenhoanganhkhoa.com.models;

import java.util.List;

public class Month {
    private String monthTrans;
    private List<Transaction> transactions;

    public String getMonthTrans() {
        return monthTrans;
    }

    public void setMonthTrans(String monthTrans) {
        this.monthTrans = monthTrans;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Month(String monthTrans, List<Transaction> transactions) {
        this.monthTrans = monthTrans;
        this.transactions = transactions;
    }
}

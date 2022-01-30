
package nguyenhoanganhkhoa.com.models;

import java.util.List;

public class Month {
    private String monthTrans;
    private List<Transaction> transactions;
    private double monthIncome;
    private double monthExpense;

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

    public double getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(double monthIncome) {
        this.monthIncome = monthIncome;
    }

    public double getMonthExpense() {
        return monthExpense;
    }

    public void setMonthExpense(double monthExpense) {
        this.monthExpense = monthExpense;
    }

    public Month(String monthTrans, List<Transaction> transactions, double monthIncome, double monthExpense) {
        this.monthTrans = monthTrans;
        this.transactions = transactions;
        this.monthIncome = monthIncome;
        this.monthExpense = monthExpense;
    }
}

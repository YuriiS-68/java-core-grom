package lesson20.task2;

public class Utils {
    private int limitTransactionsPerDayCount;
    private int limitSimpleTransactionAmount;
    private int limitTransactionsPerDayAmount;
    private String[] cities;

    public int getLimitTransactionsPerDayCount() {
        return limitTransactionsPerDayCount;
    }

    public void setLimitTransactionsPerDayCount(int limitTransactionsPerDayCount) {
        this.limitTransactionsPerDayCount = limitTransactionsPerDayCount;
    }

    public int getLimitSimpleTransactionAmount() {
        return limitSimpleTransactionAmount;
    }

    public void setLimitSimpleTransactionAmount(int limitSimpleTransactionAmount) {
        this.limitSimpleTransactionAmount = limitSimpleTransactionAmount;
    }

    public int getLimitTransactionsPerDayAmount() {
        return limitTransactionsPerDayAmount;
    }

    public void setLimitTransactionsPerDayAmount(int limitTransactionsPerDayAmount) {
        this.limitTransactionsPerDayAmount = limitTransactionsPerDayAmount;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }
}

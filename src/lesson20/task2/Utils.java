package lesson20.task2;

public class Utils {
    private int sumAmountOfTransactionsPerDay;
    private int countOfTransactionsPerDay;
    private int transactionAmountLimit;
    private String[] citiesAllowed;

    public String[] getCitiesAllowed() {
        return citiesAllowed;
    }

    public void setCitiesAllowed(String[] citiesAllowed) {
        this.citiesAllowed = citiesAllowed;
    }

    public int getTransactionAmountLimit() {
        return transactionAmountLimit;
    }

    public void setTransactionAmountLimit(int transactionAmountLimit) {
        this.transactionAmountLimit = transactionAmountLimit;
    }

    public int getSumAmountOfTransactionsPerDay() {
        return sumAmountOfTransactionsPerDay;
    }

    public void setSumAmountOfTransactionsPerDay(int sumAmountOfTransactionsPerDay) {
        this.sumAmountOfTransactionsPerDay = sumAmountOfTransactionsPerDay;
    }

    public int getCountOfTransactionsPerDay() {
        return countOfTransactionsPerDay;
    }

    public void setCountOfTransactionsPerDay(int countOfTransactionsPerDay) {
        this.countOfTransactionsPerDay = countOfTransactionsPerDay;
    }
}

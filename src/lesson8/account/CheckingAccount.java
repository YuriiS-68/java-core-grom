package lesson8.account;

/**
 * Created by Skorodielov on 09.06.2017.
 */
public class CheckingAccount extends Account {
    int limitOfExpenses;

    public CheckingAccount(String bankName, String ownerName, int moneyAmount, int limitOfExpenses) {
        super(bankName, ownerName, moneyAmount);
        this.limitOfExpenses = limitOfExpenses;
    }

    void withdraw(int amount){
        if (amount > limitOfExpenses)
            return;
        moneyAmount -= amount;
    }
    //4
}

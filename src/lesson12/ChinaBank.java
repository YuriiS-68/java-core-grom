package lesson12;

/**
 * Created by Skorodielov on 04.07.2017.
 */
public class ChinaBank extends Bank {
    public ChinaBank(long id, String bankCountry, Currency currency, int numberOfEmployees, double avrSalaryOfEmployee, long rating, long totalCapital) {
        super(id, bankCountry, currency, numberOfEmployees, avrSalaryOfEmployee, rating, totalCapital);
    }

    @Override
    public int getLimitOfWithdrawal() {
        if (getCurrency() == Currency.USD)
            return 100;
        return 150;
    }

    @Override
    public int getLimitOfFunding() {
        if (getCurrency() == Currency.EUR)
            return 5000;
        return 10000;
    }

    @Override
    public double getMonthlyRate() {
        if (getCurrency() == Currency.USD)
            return 0.01;
        else
            return 0;
    }

    @Override
    public double getCommission(int amount) {
        if (getCurrency() == Currency.USD){
            if (amount <= 1000)
                return 0.03;
            else
                return 0.05;
        }
        else {
            if (amount <= 1000)
                return 0.1;
            else
                return 0.11;
        }
    }
}

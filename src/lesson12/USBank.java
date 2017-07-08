package lesson12;

/**
 * Created by Skorodielov on 03.07.2017.
 */
public class USBank extends Bank {

    public USBank(long id, String bankCountry, Currency currency, int numberOfEmployees, double avrSalaryOfEmployee, long rating, long totalCapital) {
        super(id, bankCountry, currency, numberOfEmployees, avrSalaryOfEmployee, rating, totalCapital);
    }

    @Override
    public int getLimitOfWithdrawal() {
        //limit of withdrawal = 1000 if currency is USD and 1200 if currency is EUR
        if (getCurrency() == Currency.USD)
            return 1000;
        return 1200;
    }

    @Override
    public int getLimitOfFunding() {
        if (getCurrency() == Currency.EUR)
            return 10000;
        return Integer.MAX_VALUE;
    }

    @Override
    public double getMonthlyRate() {
        //monthly rate - 1% with USD and 2% with EUR
        if (getCurrency() == Currency.EUR)
            return 0.02;
        else
            return 0.01;
    }

    @Override
    public double getCommission(int amount) {
        //commission - 5% if USD and up to 1000, 7% if USD and more than 1000
        //6% if EUR and up to 1000 and 8% if EUR and more than 1000
        if (getCurrency() == Currency.USD){
            if (amount <= 1000)
                return 0.05;
            else
                return 0.07;
        }
        else {
            if (amount <= 1000)
                return 0.06;
            else
                return 0.08;
        }
    }
}

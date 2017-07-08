package lesson12;

/**
 * Created by Skorodielov on 03.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        Bank euBank = new EUBank(1222, "Sweden", Currency.EUR, 100, 1400, 4, 444343434);
        Bank usBank = new USBank(2355, "USA", Currency.USD, 85, 1800, 4, 55575789);
        Bank chinaBank = new ChinaBank(4276, "China", Currency.USD, 77, 1000, 3, 8459763);

        User user1 = new User(1001, "Denis", 12500, 40, "GMD", 1500, euBank);
        User user2 = new User(1002, "Jack", 24000, 20, "AMD", 1300, euBank);
        User user3 = new User(1003, "Bill", 11000, 16, "DMD", 2000, usBank);
        User user4 = new User(1004, "Peter", 8000, 8, "CMD", 1600, usBank);
        User user5 = new User(1005, "Gustav", 19000, 24, "GYMD", 900, chinaBank);
        User user6 = new User(1006, "Bruce", 20000, 12, "GAMD", 750, chinaBank);

        BankSystem bankSystem = new UkrainianBankSystem();
        bankSystem.withdraw(user1, 1150);

        System.out.println(user1.toString());
        System.out.println(user1.getBalance());
        bankSystem.fund(user1, 700);
        System.out.println(user1.getBalance());
        bankSystem.paySalary(user1);
        System.out.println(user1.getBalance());

        System.out.println();
        System.out.println(user1.getBalance());
        System.out.println(user4.getBalance());
        bankSystem.transferMoney(user1, user4, 800);
        System.out.println();
        System.out.println(user1.getBalance());
        System.out.println(user4.getBalance());

        System.out.println("The End");
    }
}

package lesson12;

/**
 * Created by Skorodielov on 03.07.2017.
 */
public class UkrainianBankSystem implements BankSystem {
    @Override
    public void withdraw(User user, int amount) {
        //проверить можно ли снять - проверить лимит, проверить достаточно ли денег
        //снять деньги

//        int limitOfWithdrawal = user.getBank().getLimitOfWithdrawal();
//        if (amount + user.getBank().getCommission(amount) > limitOfWithdrawal){
//            printWithdrawalErrorMsg(amount, user);
//            return;
//        }

//        if (amount + user.getBank().getCommission(amount) > user.getBalance()){
//            printWithdrawalErrorMsg(amount, user);
//            return;
//        }
        if (!checkWithdraw(user, amount))
            return;
        user.setBalance(user.getBalance() - amount - amount * user.getBank().getCommission(amount));
    }

    @Override
    public void fund(User user, int amount) {
        //проверить можно ли пополнить - проверить лимит, проверить пользователя
        //пополнить деньги
        //if (amount > user.getBank().getLimitOfFunding()){
        //    printFundingErrorMsg(user, amount);
        //    return;
        //}

        //if (user.getName() == null){
        //    printFundingErrorMsg(user, amount);
        //    return;
        //}
        if (!checkFunding(user, amount))
            return;
        user.setBalance(user.getBalance() + amount);

    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        //снимаем деньги с fromUser
        //пополняем toUser
        if (fromUser.getBank().getCurrency() != toUser.getBank().getCurrency()){
            System.out.println("It is not possible to transfer money from the " + fromUser.toString() + "to the " + toUser.toString());
            return;
        }
        if (!checkWithdraw(fromUser, amount))
            return;
        if (!checkFunding(toUser, amount))
            return;
        //TODO check fund rules

        fromUser.setBalance(fromUser.getBalance() - amount - amount * fromUser.getBank().getCommission(amount));
        //TODO fund
        toUser.setBalance(toUser.getBalance() + amount);
    }

    @Override
    public void paySalary(User user) {
        if (user.getName() == null || user.getSalary() > user.getBank().getLimitOfFunding()){
            System.out.println("Can't paying salary from user " + user.toString());
            return;
        }
        user.setBalance(user.getBalance() + user.getSalary());
    }

    private boolean checkWithdraw(User user, int amount){
        return checkWithdrawLimits(user, amount, user.getBank().getLimitOfWithdrawal()) &&
                checkWithdrawLimits(user, amount, user.getBalance());
    }

    private boolean checkWithdrawLimits(User user, int amount, double limit){
        if (amount + user.getBank().getCommission(amount) > limit){
            printWithdrawalErrorMsg(amount, user);
            return false;
        }
        return true;
    }

    private void printWithdrawalErrorMsg(int amount, User user){
        System.err.println("Can't withdraw money " + amount + " from user " + user.toString());
    }

    private boolean checkFundingLimits(User user, int amount, double limit){
        if (amount > user.getBank().getLimitOfFunding()){
            printFundingErrorMsg(user, amount);
            return false;
        }
        return true;
    }

    private boolean checkFundingUser(User user, int amount){
        if (user.getName() == null){
            printFundingErrorMsg(user, amount);
            return false;
        }
        return true;
    }

    private boolean checkFunding(User user, int amount){
        return checkFundingLimits(user, amount, user.getBank().getLimitOfFunding()) &&
                checkFundingUser(user, amount);

    }

    private void printFundingErrorMsg(User user, int amount){
        System.err.println("Can't funding money " + amount + " for user " + user.toString());
    }
}

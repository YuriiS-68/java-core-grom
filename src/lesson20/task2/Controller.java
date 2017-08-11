package lesson20.task2;

import lesson20.task2.exception.BadRequestException;
import lesson20.task2.exception.LimitExceeded;

public class Controller {

    //bad way
    private TransactionDAO transactionDAO = new TransactionDAO();

    private Utils utils = new Utils();

    public Controller(){
        utils.setLimitTransactionsPerDayCount(5);
        utils.setLimitTransactionsPerDayAmount(100);
        utils.setLimitSimpleTransactionAmount(40);
        utils.setCities(new String[] {"Kiev, Odesa, Mykolayiv"});
    }
    Transaction saveTransaction(Transaction transaction)throws Exception{
        Transaction[] transactions = transactionDAO.getTransactionsPerDay(transaction.getDateCreated());

        if (transaction.getAmount() > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Amount of this transaction exceeded");

        if (transactions.length + 1 > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Count of transactions per day exceeded");

        if (transactionsPerDayAmount(transactions) + transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Amount of transactions per day exceeded");

        //TODO
        //- если город оплаты(совершения транзакции) не разрешен
        if (!checkFromCityTransaction(utils.getCities(), transaction))
            throw new BadRequestException("From this city: " + transaction.getCity() + " payment is not possible");

        transactionDAO.save(transaction);

        return transaction;
    }

    Transaction[] allTransactions(){

        return null;
    }

    Transaction[] allTransactionsByCity(String city){

        return null;
    }

    Transaction[] allTransactions(int amount){

        return null;
    }

    private boolean checkFromCityTransaction(String[] citiesAllowed, Transaction transaction){
        for(String city : citiesAllowed){
            if (transaction.getCity() != null && city.equals(transaction.getCity())){
                return true;
            }
        }
        return false;
    }

    private int transactionsPerDayAmount(Transaction[] transactions){
        int amount = 0;
        for(Transaction tr : transactions){
            amount += tr.getAmount();
        }
        return amount;
    }
}

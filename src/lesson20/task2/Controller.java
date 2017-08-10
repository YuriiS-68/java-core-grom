package lesson20.task2;

public class Controller {

    //bad way
    private TransactionDAO transactionDAO = new TransactionDAO();

    private Utils utils = new Utils();

    public Controller(){
        utils.setCountOfTransactionsPerDay(5);
        utils.setSumAmountOfTransactionsPerDay(100);
        utils.setTransactionAmountLimit(40);
        utils.setCitiesAllowed(new String[] {"Kiev, Odesa, Mykolayiv"});
    }
    Transaction saveTransaction(Transaction transaction)throws Exception{
        Transaction[] transactions = transactionDAO.getTransactionsPerDay(transaction.getDateCreated());

        if (transaction.getAmount() > utils.getTransactionAmountLimit())
            throw new LimitExceeded("Amount of this transaction exceeded");

        if (transactions.length + 1 > utils.getCountOfTransactionsPerDay())
            throw new LimitExceeded("Count of transactions per day exceeded");

        if (transactionsPerDayAmount(transactions) + transaction.getAmount() > utils.getSumAmountOfTransactionsPerDay())
            throw new LimitExceeded("Amount of transactions per day exceeded");

        //TODO
        //- если город оплаты(совершения транзакции) не разрешен

        transactionDAO.save(transaction);

        return transaction;
    }

    private int transactionsPerDayAmount(Transaction[] transactions){
        int amount = 0;
        for(Transaction tr : transactions){
            amount += tr.getAmount();
        }
        return amount;
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
}

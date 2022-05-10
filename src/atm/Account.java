package atm;

import java.util.ArrayList;

public class Account {

    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    /**
     *
     * @param name the name of account
     * @param holder the user object
     * that holds this account
     * @param theBank the bank that
     * issues the account
     */
    public Account(String name , User holder , Bank theBank) {

        //set the account's name and holder        
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //inits transaction
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return this.uuid;
    }

    /**
     * get the summary line for the
     * account
     *
     * @return the string summary
     */
    public String getSummaryLine() {

        // get the account's balance
        double balance = this.getBalance();

        //format the summary line, depending on the whether the balance is negative
        if (balance >= 0) {
            return String.format("%s, $%.02f: %s" , this.uuid , balance , this.name);
        } else {
            return String.format("%s, $(%.02f): %s" , this.uuid , balance , this.name);
        }
    }

    /**
     * get the balance of this account
     * by adding the amount of the
     * transactions
     *
     * @return the balance value
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * print the transaction history of
     * the account
     */
    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n" , this.uuid);
        for (int t = this.transactions.size() - 1 ; t >= 0 ; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this
     * account
     *
     * @param amount
     * @param memo
     */
    public void addTransaction(double amount , String memo) {
        Transaction newTrans = new Transaction(amount , memo , this);
        this.transactions.add(newTrans);
    }
}

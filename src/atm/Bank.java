package atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;
    private ArrayList<Account> accounts;
    private ArrayList<User> users;

    /**
     * create the bank object with empty
     * lists of users and accounts
     *
     * @param name
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * generate a new universal unique
     * ID for a user
     *
     * @return the UUID
     */
    public String getNewUSerUUID() {

        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping untill we get unique ID
        do {

            //generate the number
            uuid = "";
            for (int c = 0 ; c < len ; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * generate a new universal unique
     * ID for an account
     *
     * @return the UUID
     */
    public String getNewAccountUUID() {

        //inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping untill we get unique ID
        do {

            //generate the number
            uuid = "";
            for (int c = 0 ; c < len ; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * add an account
     *
     * @param anAcct
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * create anew user of the bank
     *
     * @param firstName
     * @param lastName
     * @param pin
     * @return the new user object
     */
    public User addUser(String firstName , String lastName , String pin) {

        // create a new user object an add it to our list
        User newUser = new User(firstName , lastName , pin , this);
        this.users.add(newUser);

        // create a savings account for the user and add to the bank accounts lists
        Account newAccount = new Account("Savings" , newUser , this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * get the user object associated
     * with a particular useID and pin,
     * if they are valid
     *
     * @param userID
     * @param pin
     * @return the user object, if the
     * login is successful, or null, if
     * it is not
     */
    public User userLogin(String userID , String pin) {

        // search through list of users
        for (User u : this.users) {

            // check user id is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }

        }

        // if we haven't found the user or have incorrect pin
        return null;
    }

    /**
     * get the name of the bank
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
}

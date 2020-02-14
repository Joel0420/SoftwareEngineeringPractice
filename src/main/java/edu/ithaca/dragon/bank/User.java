package edu.ithaca.dragon.bank;

import java.util.HashMap;

public class User {
    private HashMap<String, BankAccount> accountCollection = new HashMap<String, BankAccount>();

    public User (String id){

    }

    public void seeAccounts(){
        if (accountCollection.size() != 0){
            for (HashMap.Entry<String,BankAccount> entry : accountCollection.entrySet()){
                System.out.println(entry.getValue().email + " " + entry.getValue().getBalance() +"\n");
                System.out.println("-------------------------------------");
            }
        }
        else {
            System.out.println("Null");
        }

    }

    public void deleteAccount(String email) throws IllegalArgumentException{
        if (accountCollection.containsKey(email)){
             accountCollection.remove(email);
        }
        else {
            throw new IllegalArgumentException("The email you entered cannot be found in your list of accounts");
        }
    }

    public void addAccount(String email, String password, double startingBalance) throws IllegalArgumentException{
        if (accountCollection.containsKey(email)){
            throw new IllegalArgumentException("This email is already in use by another one of your accounts.");
        }
        else{
            BankAccount newAccount = new BankAccount(email,startingBalance,password);
            accountCollection.put(email,newAccount);
        }
    }

    public double checkBalanceOfAccount(String email) throws IllegalArgumentException{
        if (accountCollection.containsKey(email)){
            return accountCollection.get(email).getBalance();
        }
        else {
            throw new IllegalArgumentException("This account email cannot be found in your list of BankAccounts");
        }

    }

}

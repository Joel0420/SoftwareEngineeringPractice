package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;


public class CentralBank implements AdvancedAPI, AdminAPI {

    private HashMap<String, BankAccount> customerCollection = new HashMap<String, BankAccount>();
    private HashMap<String, BankAccount> frozenAccountCollection = new HashMap<String, BankAccount>();


    public CentralBank (){

    }

    private boolean isAmountValid(double amount) throws IllegalArgumentException{
        String numString = Double.toString(amount);
        int length = numString.length();

        if (length > 3){ //only runs this test if amount has more than 3 chars
            int period = numString.lastIndexOf(".");
            if (length > (period + 3)){
                throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it has more than three decimal places.");
            }
        }
        else if (amount < 0){  //checks ifd the number is negative
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it is negative");
        }

        return true; // returns true if amount is valid

    }

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        for (int i = 0; i < customerCollection.size(); i++) {
            if (customerCollection.containsKey(acctId)) {
                BankAccount bankAccount = customerCollection.get(acctId);
                if (bankAccount.getPassword() == password){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public double checkBalance(String acctId) {
        if (customerCollection.containsKey(acctId)) {
            BankAccount bankAccount = customerCollection.get(acctId);
            return bankAccount.getBalance();
        }
        throw new IllegalArgumentException("please provide valid accout ID");
    }

    @Override
    public void withdraw(String acctId, double amount) throws InsufficientFundsException {
        BankAccount bankAccount = customerCollection.get(acctId);

        if (isAmountValid(amount) != true) {
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        if (amount < .01){ //checks that withdraw amount isnt 0
            throw new IllegalArgumentException("Cannot withdraw $0 or less");
        }
        if (bankAccount.getBalance() < amount){ //checks that you have sufficient funds for the withdraw.
            throw new InsufficientFundsException("Cannot draw more than account balance.");
        }
        else {
            bankAccount.withdraw(amount);
        }

    }

    @Override
    public void deposit(String acctId, double amount) throws IllegalArgumentException {
        BankAccount bankAccount = customerCollection.get(acctId);
        if (isAmountValid(amount) != true) {
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        if (amount <= 0){ //checks that deposit amount isn't 0
            throw new IllegalArgumentException("Cannot deposit $0");
        }
        if (amount < .01){ //checks that deposit amount isnt 0
            throw new IllegalArgumentException("Cannot deposit less than $0.01");
        }
        else {
            bankAccount.deposit(amount);
        }

    }

    @Override
    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
        BankAccount withdrawBankAccount = customerCollection.get(acctIdToWithdrawFrom);
        BankAccount depositBankAccount = customerCollection.get(acctIdToDepositTo);
        if (isAmountValid(amount) != true) {
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        if (amount == 0) { //checks that amount isn't 0
            throw new IllegalArgumentException("Cannot deposit $0");
        }
        if (withdrawBankAccount.balance < amount){
            throw new InsufficientFundsException("amount you wish to withdraw exceeds balance");
        }
        else{
            withdrawBankAccount.transfer(depositBankAccount,amount);
        }
    }

    @Override
    public String transactionHistory(String acctId) {
        if(checkCustomerCollection(acctId) == false) {
            BankAccount bankAccount = customerCollection.get(acctId);
            int transCount = bankAccount.getTransferCount();
            int depoCount = bankAccount.getDepositCount();
            int withCount = bankAccount.getWithdrawCount();
            int total = bankAccount.getDepositCount()+bankAccount.getTransferCount()+bankAccount.getWithdrawCount();
            String transHistory = "Total number of transactions done on account: "+total + " (Deposits: " + depoCount+ " Withdraws: "+withCount+ " Transfers: "+transCount+")";
            return transHistory;
        }
        return null;
    }

    @Override
    public double calcTotalAssets() {
        double total = 0;


        for (HashMap.Entry<String,BankAccount> entry : customerCollection.entrySet()){
            total += entry.getValue().getBalance();
        }
        return total;
    }

    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    @Override
    public void freezeAccount(String acctId) {
        BankAccount bankAccount = null;
        if (customerCollection.containsValue(acctId)){
            bankAccount = customerCollection.get(acctId);
        }
        frozenAccountCollection.put(acctId,bankAccount);
    }

    @Override
    public void unfreezeAcct(String acctId) {
        BankAccount bankAccount = null;
        if (frozenAccountCollection.containsValue(acctId)){
            bankAccount = frozenAccountCollection.get(acctId);
        }
        customerCollection.put(acctId,bankAccount);
    }

    @Override
    public void createAccount(String acctId, double startingBalance, String password) {
        BankAccount bankAccount = new BankAccount(acctId,startingBalance, password);
        customerCollection.put(acctId,bankAccount);
    }

    @Override
    public void closeAccount(String acctId) throws IllegalArgumentException {
        if (customerCollection.containsKey(acctId)) {
            BankAccount bankAccount = customerCollection.get(acctId);
            customerCollection.remove(acctId);
            bankAccount = null;
        }
        else{
            throw new IllegalArgumentException(acctId+ " does not exist");
        }
    }

    //stub for test case
    public boolean checkCustomerCollection(String acctId){
        if (customerCollection.containsKey(acctId)){
            return false;
        }
        return true;
    }


    /*public double checkBalance(String acctId, BankAccount[] customerCollection) throws IllegalArgumentException {
        int length = customerCollection.length;
        for(int i =0; i < length; i++){
            if (acctId == customerCollection[i].email)
                return customerCollection[i].getBalance();
        }
        throw new IllegalArgumentException("The account ID you entered is not in the system");
    }
    public void withdraw(String acctId, double amount, BankAccount[] customerCollection) throws InsufficientFundsException {
        int length = customerCollection.length;
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        if (amount < .01) //checks that withdraw amount isnt 0
            throw new IllegalArgumentException("Cannot withdraw $0 or less");
        for(int i =0; i < length; i++){
            if (acctId == customerCollection[i].email) {
                if (customerCollection[i].balance < amount) //checks that you have sufficient funds for the withdraw.
                    throw new InsufficientFundsException("Cannot draw more than account balance.");
                else
                    customerCollection[i].balance -= amount; //takes out money if everything is good
            }
        }
    }
    public void deposit(String acctId, double amount, BankAccount[] customerCollection) {
        int length = customerCollection.length;
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        else if (amount == 0){
            throw new IllegalArgumentException("You cannot deposit $0");
        }
        else
            for(int i =0; i < length; i++){
                if (acctId == customerCollection[i].email)
                    customerCollection[i].balance += amount;
            }
    }
    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
    }
    public String transactionHistory(String acctId) {
        return null;
    }
    //----------------- AdvancedAPI methods -------------------------//
    public void createAccount(String acctId, double startingBalance) {
    }
    public void closeAccount(String acctId) {
    }
    //------------------ AdminAPI methods -------------------------//
    public double checkTotalAssets() {
        return 0;
    }
    public double calcTotalAssets() {
        return 0;
    }
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }
    public void freezeAccount(String acctId) {
    }
    public void unfreezeAcct(String acctId) {
    }
*/
}
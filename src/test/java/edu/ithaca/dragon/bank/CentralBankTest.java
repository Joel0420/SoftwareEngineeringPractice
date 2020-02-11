package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {


       /* @Test
        void checkBalance() {
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305, "abcd1234");

            //checks the balance of an account in the collection
            //BankAccount bankAccount = new BankAccount("a@b.com",305, "abcd1234");
            CentralBank cb = new CentralBank();
            assertEquals(305, cb.checkBalance("a@b.com",customerCollection));

            //asks for the balance of an account not in the collection
            assertThrows(IllegalArgumentException.class, ()-> cb.checkBalance("ab@c.com",customerCollection));

        }

        @Test
        void depositTest() {
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305, "abcd1234");

            //deposits a valid amount into a valid bank account
            CentralBank cb = new CentralBank();
            cb.deposit("a@b.com",50, customerCollection);
            assertEquals(355, cb.checkBalance("a@b.com",customerCollection));

            //attempts to deposit an invalid amount
            assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",5000.608,customerCollection));

            //attempts to deposit 0
            assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",0,customerCollection));


        }*/
        @Test
        void confirmCredentialsTest(){
            CentralBank cB = new CentralBank();
            cB.createAccount("ppatel@ithaca.edu",500,"ITH19");
            cB.createAccount("mdad@ithaca.edu",500,"ITH20");
            cB.createAccount("kweal@ithaca.edu",500,"ITH21");

            assertTrue(cB.confirmCredentials("ppatel@ithaca.edu","ITH19"));
            assertTrue(cB.confirmCredentials("mdad@ithaca.edu","ITH20"));
            assertTrue(cB.confirmCredentials("kweal@ithaca.edu","ITH21"));

            assertFalse(cB.confirmCredentials("Beefstew@rolling.org","WER1"));
            assertFalse(cB.confirmCredentials("Cornbeef@rocks.com","DANCER23"));
            assertFalse(cB.confirmCredentials("sloppyJO@beverages.net","HollY23!"));


        }
        @Test
        void withdrawTest() throws InsufficientFundsException {
            CentralBank cb = new CentralBank();

            cb.createAccount("a@b.com", 400, "ABCD1234");

            //withdraws a valid amount with sufficient funds
            cb.withdraw("a@b.com",200);
            assertEquals(200,cb.checkBalance("a@b.com"));

            //withdraws an invalid amount with sufficient funds
            assertThrows(IllegalArgumentException.class, ()-> cb.withdraw("a@b.com",-20.9088));

            //withdraws a valid amount with insufficient funds
            assertThrows(InsufficientFundsException.class, ()-> cb.withdraw("a@b.com",560));


        }
}


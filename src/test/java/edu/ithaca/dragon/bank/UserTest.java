package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    User jiggy = new User("1");

    @Test
    void addAccountTest() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> jiggy.checkBalanceOfAccount("a@b.com"));
        jiggy.addAccount("a@b.com","a",200);
        assertEquals(200,jiggy.checkBalanceOfAccount("a@b.com"));

    }

    @Test
    void seeAccountsTest() throws IllegalArgumentException{
        jiggy.addAccount("b@c.com","b",340.50);
        jiggy.seeAccounts();
        System.out.println("Expected: \nb@c.com 340.50 \n");
        System.out.println("-------------------------------------");

        jiggy.addAccount("d@f.com", "c",40000.10);
        jiggy.seeAccounts();

        System.out.println("Expected: \nd@f.com 40000.10\nb@c.com 340.50 " );
        System.out.println("-------------------------------------");
    }

    @Test
    void deleteAccountTest(){
        jiggy.addAccount("a@b.com","a",200);
        jiggy.seeAccounts();
        assertEquals(200, jiggy.checkBalanceOfAccount("a@b.com"));

        jiggy.deleteAccount("a@b.com");
        assertThrows(IllegalArgumentException.class, () -> jiggy.checkBalanceOfAccount("a@b.com"));
        jiggy.seeAccounts();
    }

}

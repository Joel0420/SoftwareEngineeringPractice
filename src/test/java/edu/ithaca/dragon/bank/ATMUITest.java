package edu.ithaca.dragon.bank;

public class ATMUITest {
    public static void main(String[] args) {
        CentralBank testCB = CentralBankTest.buildTestObject();

        BasicAPI testAPI = testCB;

        ATMUI testAtmUI = new ATMUI(testAPI);




    }
}

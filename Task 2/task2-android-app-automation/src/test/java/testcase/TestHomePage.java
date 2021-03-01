package testcase;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.IOException;

public class TestHomePage {

    HomePage homePage;

    @BeforeTest
    public void init() throws IOException, InterruptedException {
        homePage = new HomePage();
    }

//    @Test(priority = 0,enabled = false)
//    public void testAddIncome() throws IOException, InterruptedException {
//        homePage.addIncome();
//    }
//
//    @Test(priority = 1, enabled = false)
//    public void testAddExpense() throws IOException, InterruptedException {
//        homePage.addExpense();
//    }
//
//    @Test(priority = 2, enabled = false)
//    public void testBalanceJustification() throws IOException, InterruptedException {
//        Assert.assertEquals(homePage.checkBalanceJustification(), true);
//    }
//
//    @Test(priority = 3, enabled = false)
//    public void testShowingBalanceDetails() throws IOException, InterruptedException {
//        homePage.showBalanceDetails();
//    }


    @Description("Add Expense by Cash")
    @Test(priority = 0, enabled = true, description = "Add Expense by Cash")
    public void testAddExpenseByCash() throws IOException, InterruptedException {
        homePage.addExpenseByCash();
    }
    @Description("Add Income by Cash")
    @Test(priority = 1,enabled = true, description = "Add Income by Cash")
    public void testAddIncomeByCash() throws IOException, InterruptedException {
        homePage.addIncomeByCash();
    }
    @Description("Add Expense by Card")
    @Test(priority = 2, enabled = true, description = "Add Expense by Card")
    public void testAddExpenseByCard() throws IOException, InterruptedException {
        homePage.addExpenseByCard();
    }
    @Description("Add Income by Card")
    @Test(priority = 3,enabled = true, description = "Add Income by Card")
    public void testAddIncomeByCard() throws IOException, InterruptedException {
        homePage.addIncomeByCard();
    }

//    @Test(priority = 2,enabled = true)
//    public void testShowBalanceDetails() throws IOException, InterruptedException {
//        homePage.showBalanceDetails();
//    }
    @Description("Check Left Menu Account type is selected as Cash")
    @Test(priority = 4,enabled = true, description = "Check Left Menu Account type is selected as Cash")
    public void testMenuCash() throws IOException, InterruptedException {
        homePage.checkMenuCash();
    }
    @Description("Check Left Menu Account type is selected as Card")
    @Test(priority = 5,enabled = true, description = "Check Left Menu Account type is selected as Card")
    public void testMenuCard() throws IOException, InterruptedException {
        homePage.checkMenuCard();
    }
    @Description("Check Left Menu Account type is selected as All Accounts")
    @Test(priority = 6,enabled = true, description = "Check Left Menu Account type is selected as All Accounts")
    public void testAllAcc() throws IOException, InterruptedException {
        homePage.checkAllAcc();
    }
}

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

    @Test(priority = 1, enabled = true, description = "Add Income by Cash")
    public void testAddIncomeByCash() throws IOException, InterruptedException {
        homePage.addIncomeByCash();
    }

    @Test(priority = 2, enabled = true, description = "Add Expense by Cash")
    public void testAddExpenseByCash() throws IOException, InterruptedException {
        homePage.addExpenseByCash();
    }

    @Test(priority = 3, enabled = true, description = "In Cash, Check whether balance calculation is exact or not after adding income and expense")
    public void testBalanceJustificationForCashIncomeAndExpense() throws IOException, InterruptedException {
        Assert.assertEquals(homePage.checkBalanceJustification("3000"), true);
    }

    @Test(priority = 4, enabled = true, description = "Add Income by Card")
    public void testAddIncomeByCard() throws IOException, InterruptedException {
        homePage.addIncomeByCard();
    }

    @Test(priority = 5, enabled = true, description = "Add Expense by Card")
    public void testAddExpenseByCard() throws IOException, InterruptedException {
        homePage.addExpenseByCard();
    }

    @Test(priority = 6, enabled = true, description = "In Card, Check whether balance calculation is exact or not after adding income and expense")
    public void testBalanceJustificationForCardIncomeAndExpense() throws IOException, InterruptedException {
        Assert.assertEquals(homePage.checkBalanceJustification("3000"), true);
    }

    @Test(priority = 7, enabled = true, description = "For both card and cash, Check whether balance calculation is exact or not after adding income and expense")
    public void testBalanceJustificationForCashAndCardIncomeAndExpense() throws IOException, InterruptedException {
        homePage.switchToAllAccount();
        Assert.assertEquals(homePage.checkBalanceJustification("6000"), true);
    }

    @Test(priority = 8, enabled = true, description = "Check balance details for cash only")
    public void testMenuCash() throws IOException, InterruptedException {
        homePage.checkMenuCash();
    }

    @Test(priority = 9, enabled = true, description = "Check balance details for cash only")
    public void testMenuCard() throws IOException, InterruptedException {
        homePage.checkMenuCard();
    }

    @Test(priority = 10, enabled = true, description = "Check balance details for cash and card both")
    public void testAllAcc() throws IOException, InterruptedException {
        homePage.checkAllAcc();
    }
}

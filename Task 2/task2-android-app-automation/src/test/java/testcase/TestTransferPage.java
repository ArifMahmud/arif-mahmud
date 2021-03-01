package testcase;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TransferPage;

import java.io.IOException;

public class TestTransferPage {

    TransferPage transferPage;

    @BeforeTest
    public void init() throws IOException, InterruptedException {
        transferPage = new TransferPage();
    }
    @Description("Check whether Transferring amount from Card to Cash")
    @Test(priority = 0, enabled = true, description = "Check whether Transferring amount from Card to Cash")
    public void testClickTransferCardToCash() throws IOException, InterruptedException {
        transferPage.checkTransferCardToCash();
    }
    @Description("Check whether Transferring amount from Cash to Card")
    @Test(priority = 1, enabled = true, description = "Check whether Transferring amount from Cash to Card")
    public void testClickTransferCashToCard() throws IOException, InterruptedException {
        transferPage.checkTransferCashToCard();
    }
}

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

    @Test(priority = 11, enabled = true, description = "Check whether Transferring amount can be done from Card to Cash")
    public void testClickTransferCardToCash() throws IOException, InterruptedException {
        transferPage.checkTransferCardToCash();
    }

    @Test(priority = 12, enabled = true, description = "Check whether Transferring amount can be done from Cash to Card")
    public void testClickTransferCashToCard() throws IOException, InterruptedException {
        transferPage.checkTransferCashToCard();
    }
}

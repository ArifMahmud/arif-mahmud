package testcase;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MenuPage;

import java.io.IOException;

public class TestMenuPage {

    MenuPage menuPage;

    @BeforeTest
    public void init() throws IOException, InterruptedException {
        menuPage = new MenuPage();
    }


    @Description("Check whether menu is clickable or not")
    @Test(priority = 0, enabled = true, description = "Check whether menu is clickable or not")
    public void testClickMenu() throws IOException, InterruptedException {
        menuPage.checkMenuClickable();
    }
}

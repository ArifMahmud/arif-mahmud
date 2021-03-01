package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Path;
import util.Util;

import java.io.IOException;

public class MenuPage extends BasePage{
    public MenuPage() throws IOException, InterruptedException {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = Path.menuBtnXpath)
    private WebElement menu;

    @FindBy(xpath = Path.menuBackBtnXpath)
    private WebElement menuBack;


    public void checkMenuClickable(){
        menu.click();
        Util.attachScreenShot(driver);
        menuBack.click();
        Util.attachScreenShot(driver);
    }
}

package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Path;
import util.Util;

import java.io.IOException;

public class TransferPage extends BasePage{
    public TransferPage() throws IOException, InterruptedException {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = Path.transferBtnId)
    private WebElement transferBtnId;

//  First DropDown
    @FindBy(xpath = Path.transferPageFirstDropDownXpath)
    private WebElement transferPageFirstDropDown;

    @FindBy(xpath = Path.transferPageFirstDropDownCashXpath)
    private WebElement transferPageFirstDropDownCash;

    @FindBy(xpath = Path.transferPageFirstDropDownCardXpath)
    private WebElement transferPageFirstDropDownCard;

//  Second DropDown
    @FindBy(xpath = Path.transferPageSecondDropDownXpath)
    private WebElement transferPageSecondDropDown;

    @FindBy(xpath = Path.transferPageSecondtDropDownCashXpath)
    private WebElement transferPageSecondtDropDownCash;

    @FindBy(xpath = Path.transferPageSecondDropDownCardXpath)
    private WebElement transferPageSecondDropDownCard;


    @FindBy(id = Path.transferPageAmountBtnId)
    private WebElement transferPageAmountBtnId;

//  Calculator Buttons

    @FindBy(id = Path.btnId5)
    private WebElement btn5;

    @FindBy(id = Path.btnId0)
    private WebElement btn0;

    @FindBy(id = Path.btnId1)
    private WebElement btn1;

    @FindBy(xpath = Path.addTransferButtonXpath)
    private WebElement addTransferButtonXpath;

    public void checkTransferCardToCash(){
        transferBtnId.click();
//        First Card
        transferPageFirstDropDown.click();
        transferPageFirstDropDownCard.click();
//        Second Cash
        transferPageSecondDropDown.click();
        transferPageSecondtDropDownCash.click();

        transferPageAmountBtnId.click();
        btn5.click();
        btn0.click();
        btn0.click();
        Util.attachScreenShot(driver);
        addTransferButtonXpath.click();

    }

    public void checkTransferCashToCard(){
        transferBtnId.click();
//        First Cash
        transferPageFirstDropDown.click();
        transferPageFirstDropDownCash.click();
//        Second Card
        transferPageSecondDropDown.click();
        transferPageSecondDropDownCard.click();

        transferPageAmountBtnId.click();
        btn1.click();
        btn0.click();
        btn0.click();
        Util.attachScreenShot(driver);
        addTransferButtonXpath.click();


    }
}

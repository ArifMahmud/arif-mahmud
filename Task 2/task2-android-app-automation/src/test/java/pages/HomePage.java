package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Path;
import util.Util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    Util util = new Util();

    public HomePage() throws IOException, InterruptedException {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = Path.incomeBtnId)
    private WebElement income;

    @FindBy(id = Path.expenseBtnId)
    private WebElement expense;

    @FindBy(id = Path.balanceBtnId)
    private WebElement balanceDetails;

    @FindBy(id = Path.leftMenuId)
    private WebElement leftMenu;

    @FindBy(id = Path.amountField)
    private WebElement amount;

    @FindBy(id = Path.btnId5)
    private WebElement btn5;

    @FindBy(id = Path.btnId0)
    private WebElement btn0;

    @FindBy(id = Path.btnId1)
    private WebElement btn1;

    @FindBy(id = Path.btnId2)
    private WebElement btn2;

    @FindBy(id = Path.noteFieldId)
    private WebElement note;

    @FindBy(id = Path.chooseCategoryId)
    private WebElement category;

    @FindBy(id = Path.listSortBtnId)
    private WebElement sorting;

    @FindBy(id = Path.accountSpinnerId)
    private WebElement accountSpinner;

    @FindBy(xpath = Path.accountSpinnerCashXpath)
    private WebElement accountSpinnerCash;

    @FindBy(xpath = Path.accountSpinnerCardXpath)
    private WebElement accountSpinnerCard;

    @FindBy(xpath = Path.salaryBtnXpath)
    private WebElement salary;

    @FindBy(xpath = Path.houseBtnXpath)
    private WebElement house;

    @FindBy(xpath = Path.foodBtnXpath)
    private WebElement food;

    @FindBy(xpath = Path.menuBtnXpath)
    private WebElement menu;

    @FindBy(xpath = Path.accountDropDownXpath)
    private WebElement accDropDown;

    @FindBy(xpath = Path.cardBtnXpath)
    private WebElement cardOption;

    @FindBy(xpath = Path.cashBtnXpath)
    private WebElement cashOption;

    @FindBy(xpath = Path.allAccBtnXpath)
    private WebElement allAccBtn;




    public void addIncome() throws InterruptedException, IOException {
        income.click();
        btn5.click();
        btn0.click();
        btn0.click();
        btn0.click();
        note.sendKeys("Income from Salary");
        driver.navigate().back();
        category.click();
        salary.click();
    }

    public void addExpense() throws InterruptedException, IOException {
        expense.click();
        btn5.click();
        btn0.click();
        btn0.click();
        btn0.click();
        note.sendKeys("Expense for house");
        driver.navigate().back();
        category.click();
        house.click();
    }

    public void addIncomeByCash() throws InterruptedException, IOException {
        income.click();
//        note.sendKeys("Income from Salary by Cash");
        accountSpinner.click();
        accountSpinnerCash.click();
        btn5.click();
        btn0.click();
        btn0.click();
        btn0.click();
//        driver.navigate().back();
        Util.attachScreenShot(driver);
        category.click();
        salary.click();
    }

    public void addExpenseByCash() throws InterruptedException, IOException {
        expense.click();
//        note.sendKeys("Expense for house Cash");
        accountSpinner.click();
        accountSpinnerCash.click();
        btn2.click();
        btn0.click();
        btn0.click();
        btn0.click();
//
//        driver.navigate().back();
        Util.attachScreenShot(driver);
        category.click();
        house.click();
    }

    public void addIncomeByCard() throws InterruptedException, IOException {
        income.click();
//        note.sendKeys("Income from Salary by card");
        accountSpinner.click();
        accountSpinnerCard.click();
        btn5.click();
        btn0.click();
        btn0.click();
        btn0.click();

//        driver.navigate().back();
        Util.attachScreenShot(driver);
        category.click();
        Util.attachScreenShot(driver);
        salary.click();
    }

    public void addExpenseByCard() throws InterruptedException, IOException {
        expense.click();
//        note.sendKeys("Expense for house card");
        accountSpinner.click();
        accountSpinnerCard.click();
        btn2.click();
        btn0.click();
        btn0.click();
        btn0.click();
//        driver.navigate().back();
        Util.attachScreenShot(driver);
        category.click();
        Util.attachScreenShot(driver);
        food.click();
    }

    public void checkMenuCash()  throws InterruptedException, IOException{
        menu.click();
        Util.attachScreenShot(driver);
        accDropDown.click();
        Util.attachScreenShot(driver);
        cashOption.click();
        showBalanceDetails();
        Util.attachScreenShot(driver);

    }

    public void checkMenuCard()  throws InterruptedException, IOException{
        menu.click();
        Util.attachScreenShot(driver);
        accDropDown.click();
        Util.attachScreenShot(driver);
        cardOption.click();
        showBalanceDetails();
        Util.attachScreenShot(driver);

    }

    public void checkAllAcc()  throws InterruptedException, IOException{
        menu.click();
        Util.attachScreenShot(driver);
        accDropDown.click();
        Util.attachScreenShot(driver);
        allAccBtn.click();
        showBalanceDetails();
        Util.attachScreenShot(driver);


    }

    public boolean checkBalanceJustification() throws InterruptedException, IOException {
        String currentExpectedBalance = "0.00";
        String currentActualBalance = balanceDetails.getText().substring(9);
        System.out.println("current balance is : " + currentActualBalance);
        if (currentActualBalance.equalsIgnoreCase(currentExpectedBalance))
            return true;
        else
            return false;
    }

    public void showBalanceDetails() throws InterruptedException {
        balanceDetails.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        sorting.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Util.attachScreenShot(driver);
        balanceDetails.click();
    }
}

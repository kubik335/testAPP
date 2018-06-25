package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import setup.Factory;
import setup.PageObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static setup.DriverManager.webDriver;

/**
 * Created by Alexandra Kolpakova on 20.03.2017.
 */
public class AccountsPage extends PageObject{

    @FindBy(xpath = "//button[@id=\"crtBtn\"][.=\"Create new account\"]")
    private WebElement create;

    @FindBy (xpath = "//button[@id=\"GET\"]")
    private WebElement search;

    @FindBy (xpath = "//input[@name=\"loginSearch\"]")
    private WebElement searchField;

    @FindBy(xpath = "//button[@id=\"updBtn\"]")
    private WebElement update;

    @FindBy (xpath = "//button[@id=\"detail\"]")
    private WebElement detail;

    @FindBy (xpath = "//button[@id=\"disable\"]")
    private WebElement disable;

    @FindBy (xpath = "//table//*[.=\"admin\"]")
    private WebElement admin;

    @Override
    public void synchronize() {
        waitForElements();
    }

    public AccountsPage waitForElements(){
        driverManager.waitForElement(By.xpath("//h2[.=\"Accounts\"]"), 10);
        driverManager.waitForElement(By.xpath("//button[@id=\"crtBtn\"][.=\"Create new account\"]"), 10);
        return this;
    }

    public AccountsPage createAccountButton(){
        create.click();
        driverManager.waitForElement(By.xpath("//h4[.=\"Fill in the fields to create new account\"]"), 5);
        return this;
    }

    public AccountsPage createAccount(String login, String pass, String mail, String role){
        driverManager.findElement(By.id("login")).sendKeys(login);
        driverManager.findElement(By.id("password")).sendKeys(pass);
        driverManager.findElement(By.id("email")).sendKeys(mail);

        Select dropdown = new Select(driverManager.findElement(By.id("role")));
        dropdown.selectByValue(role);

        return this;
    }

    public AccountsPage confirmCreation(){
        driverManager.findElement(By.id("createAccountConfirm")).click();
        return Factory.createPO();
    }

    public AccountsPage closeCreationConfirm(){
        driverManager.findElement(By.id("createAccountClose")).click();
        waitUntilElementDisappear("//button[@id=\"createAccountClose\"]");
        return Factory.createPO();
    }

    public boolean waitUntilElementDisappear(String xpath){
        boolean visible;
        visible = false;
        int i = 0;
        while (i == 0){
            try{
                driverManager.waitForElement(By.xpath(xpath), 1);
            }catch(Exception e){
                i = 1;
                visible = true;
            }
        }
        return visible;
    }

    public AccountsPage searchAccount(String login){
        searchField.sendKeys(login);
        search.click();
        waitUntilElementDisappear("//tr[td[.=\"admin\"]]");
        return this;
    }

    public AccountsPage checkResult(String login){
        driverManager.waitForElement(By.xpath("//td[.=\""+login+"\"]"), 10);
        return this;
    }

    public AccountsPage updateAccountButton(String login){
        driverManager.findElement(By.xpath("//tr[td[.=\""+login+"\"]]//button[@id=\"updBtn\"]")).click();
        driverManager.waitForElement(By.xpath("//h4[.=\"Update Account\"]"), 5);
        return this;
    }

    public AccountsPage updateAccount(String login, String pass, String mail, String role){
        driverManager.findElement(By.id("up_login")).clear();
        driverManager.findElement(By.id("up_password")).clear();
        driverManager.findElement(By.id("up_email")).clear();

        driverManager.findElement(By.id("up_login")).sendKeys(login);
        driverManager.findElement(By.id("up_password")).sendKeys(pass);
        driverManager.findElement(By.id("up_email")).sendKeys(mail);

        Select dropdown = new Select(driverManager.findElement(By.id("up_role")));
        dropdown.selectByValue(role);

        return this;
    }

    public AccountsPage updateConfirm(){
        driverManager.findElement(By.id("updateAccountConfirm")).click();
        waitUntilElementDisappear("updateAccountClose");
        return Factory.createPO();
    }

    public AccountsPage closeUpdateConfirm(){
        driverManager.findElement(By.id("updateAccountClose")).click();
        waitUntilElementDisappear("updateAccountClose");
        return Factory.createPO();
    }

    public AccountsPage showDetail(String login){
        driverManager.findElement(By.xpath("//tr[td[.=\""+login+"\"]]//button[@id=\"detail\"]")).click();
        driverManager.waitForElement(By.xpath("//h4[.=\"Account's detail\"]"), 5);
        driverManager.waitForElement(By.xpath("//input[@id=\"det_login\"][@disabled]"), 5);
        driverManager.findElement(By.id("detailAccountClose")).click();
        waitUntilElementDisappear("detailAccountClose");
        return this;
    }

    public AccountsPage disableAccount(String login){
        driverManager.findElement(By.xpath("//tr[td[.=\""+login+"\"]]//button[@id=\"disable\"]")).click();
        driverManager.waitForElement(By.xpath("//tr[td[.=\""+login+"\"]]//button[@id=\"enable\"]"), 5);
        return this;
    }




}

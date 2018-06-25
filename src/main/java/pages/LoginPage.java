package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import setup.Factory;
import setup.PageObject;

import static setup.DriverManager.webDriver;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public class LoginPage extends PageObject {

    @FindBy(name = "login")
    private WebElement username;

    @FindBy (name = "password")
    private WebElement password;

    @FindBy (xpath = "//button[@type=\"submit\"]")
    private WebElement submit;

    @FindBy (className = "form-control has-error")
    private WebElement error;

    @FindBy (xpath = "//a[@href=\"logout.php\"]")
    private WebElement logout;

    @Override
    public void synchronize() {
        driverManager.waitForElement(By.name("login"), 10);
        driverManager.waitForElement(By.name("password"), 10);
        driverManager.waitForElement(By.xpath("//button[@type=\"submit\"]"), 10);

    }

    public LoginPage fillCredentials(String name, String pwd){
        username.sendKeys(name);
        password.sendKeys(pwd);
        return this;
    }

    public HomePage submitCredentials(){
        submit.click();
        return Factory.createPO();
    }

    public HomePage submitCredentials2(){
        submit.click();
        try {
            Alert confirmationAlert = webDriver.switchTo().alert();
            String alertText = confirmationAlert.getText();
            System.out.println("Alert text is " + alertText);
            confirmationAlert.accept();
        } catch (TimeoutException e) {
            e.getStackTrace();
        }
        return Factory.createPO();
    }



}

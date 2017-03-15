package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import setup.PageObject;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public class HomePage extends PageObject{
    @FindBy(xpath = "//a[@href=\"#accounts\"]")
    private WebElement accountsModule;

    @FindBy (xpath = "//a[@href=\"#projects\"]")
    private WebElement projectsModule;

    @FindBy (xpath = "//a[@href=\"logout.php\"]")
    private WebElement logout;

    @Override
    public void synchronize() {
        driverManager.waitForElement(By.xpath("//h4[.=\"Available modules\"]"), 10);
        checkModules();

    }

    public HomePage checkModules(){

        String accountsModule = driverManager.findElement(By.xpath("//a[@href=\"#accounts\"]")).getText();
        String projectsModule = driverManager.findElement(By.xpath("//a[@href=\"#projects\"]")).getText();
        String logout = driverManager.findElement(By.xpath("//a[@href=\"logout.php\"]")).getText();

        assertEquals("Accounts", accountsModule);
        assertEquals("Projects", projectsModule);
        assertEquals("Logout", logout);
        return this;
    }
}

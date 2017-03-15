package delete;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandra Kolpakova on 06.03.2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LoginTest {


    static RemoteWebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void afterClass() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }


    @Test
    public void step1_checkPage() {
        driver.get("http://31.31.73.202/login.php");
        waitForElement(driver,By.id("loginForm"));
        WebElement loginText = driver.findElement(By.xpath("//form[@name=\"form\"]/h2"));
        String result=loginText.getText();


        // JUnit style assert
        assertEquals("Login to application", result);
    }

    @Test
    public void step2_loginTest() {
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        waitForElement(driver,By.xpath("//h4[.=\"Available modules\"]"));
        String accounts = driver.findElement(By.xpath("//a[@href=\"#accounts\"]")).getText();
        String projects = driver.findElement(By.xpath("//a[@href=\"#projects\"]")).getText();
        String logout = driver.findElement(By.xpath("//a[@href=\"logout.php\"]")).getText();

        assertEquals("Accounts", accounts);
        assertEquals("Projects", projects);
        assertEquals("Logout", logout);

    }


    @Test
    public void step3_logoutTest() {
        // LOG OUT
        driver.findElement(By.linkText("Logout")).click();
    }


    private void waitForElement(RemoteWebDriver driver, final By by){
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }

}
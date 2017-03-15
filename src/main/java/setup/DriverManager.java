package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public class DriverManager {



    private static DriverManager instance;

    public static RemoteWebDriver webDriver;

    public static DriverManager getInstance(){
        if(instance == null){
            instance = new DriverManager();
        }
        return instance;
    }

    private DriverManager(){
        getWebDriver();
    }

    public static RemoteWebDriver getWebDriver(){

        if(webDriver != null){
            return webDriver;
        }

        switch (Properties.getPlatform()){

            case WINDOWS:

                switch (Properties.getBrowserName()){

                    case "chrome":
                        /**try {
                            webDriver = new RemoteWebDriver(new URL("http://localhost:9515"),Properties.getCapabilities());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                         */
                        System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver");
                        webDriver = new ChromeDriver();
                        break;
                    case "firefox":
                        webDriver = new FirefoxDriver(Properties.getCapabilities());
                        break;
                    default:
                        throw new RuntimeException("Wrong browser name");

                }

                break;
            case ANDROID:
                break;
            case MAC:
                break;

        }
        webDriver.get(Properties.getUrl());
        return webDriver;
    }



    /**
     * Wait for page is loaded
     */
    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        Wait<WebDriver> wait = new WebDriverWait(webDriver, 10);
        wait.until(expectation);
    }

    /**
     *
     * @param by
     * @param timeout
     */
    public void waitForElement(final By by, long timeout) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, timeout);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }

    public WebElement findElement(By by){
        return webDriver.findElement(by);
    }

    public static void closeBrowser() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.quit();
    }

}

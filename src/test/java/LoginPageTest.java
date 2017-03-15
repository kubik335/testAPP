import listener.TestRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import setup.DriverManager;
import setup.Factory;
import setup.Properties;
import java.io.*;


/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
@RunWith(TestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTest {

    @Test
    public void login1(){
        LoginPage loginPage = Factory.createPO();
        loginPage
                .fillCredentials("admin", "admin")
                .submitCredentials();
        loginPage.logoutButton();

    }

    @Test(expected = TimeoutException.class)
    public void login2(){
        LoginPage loginPage = Factory.createPO();
        loginPage
                .fillCredentials("admin", "")
                .submitCredentials2();
    }

    @BeforeClass
    public static void setup(){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("marionette", true);
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setCapability("URL", "http://31.31.73.202/login.php");
        Properties.setCapabilities(capabilities);
    }

    @AfterClass
    public static void teardown() {
        DriverManager.closeBrowser();
    }

}

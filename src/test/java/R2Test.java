import listener.TestRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.AccountsPage;
import pages.HomePage;
import pages.LoginPage;
import setup.DriverManager;
import setup.Factory;

/**
 * Created by Alexandra Kolpakova on 20.03.2017.
 */
@RunWith(TestRunner.class)
public class R2Test {

    @Test
    public void R2_createAccount(){
        HomePage page = Factory.createPO();
        page.goToAccounts();
        AccountsPage accounts = Factory.createPO();
        accounts
                .createAccountButton()
                .closeCreationConfirm()
                .createAccountButton()
                .createAccount("honzik", "roma", "irina@irina.com", "2")
                .confirmCreation();
    }
    @BeforeClass
    public static void setup(){
        DriverManager.setting_up();
        LoginPage loginPage = Factory.createPO();
        loginPage
                .fillCredentials("admin", "admin")
                .submitCredentials();
    }

    @AfterClass
    public static void teardown() {
        DriverManager.closeBrowser();
    }

}
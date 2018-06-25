import listener.TestRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import pages.AccountsPage;
import pages.HomePage;
import pages.LoginPage;
import setup.DriverManager;
import setup.Factory;

/**
 * Created by Alexandra Kolpakova on 20.03.2017.
 */
@RunWith(TestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDTest {

    @Test
    public void R1_login() {
        LoginPage loginPage = Factory.createPO();
        loginPage
                .fillCredentials("admin", "admin")
                .submitCredentials();
        HomePage page = Factory.createPO();
        page.goToAccounts();
    }

    @Test
    public void R2_createAccount() {
        AccountsPage accounts = Factory.createPO();
        accounts
                .createAccountButton()
                .closeCreationConfirm()
                .createAccountButton()
                .createAccount("honzik", "roma", "irina@irina.com", "2")
                .confirmCreation();
    }

    @Test
    public void R3_showDetailAccount() {
        AccountsPage accounts = Factory.createPO();
        accounts
                .searchAccount("honzik")
                .checkResult("honzik")
                .showDetail("honzik");
    }

    @Test
    public void R4_updateAccount() {
        AccountsPage accounts = Factory.createPO();
        accounts
                .updateAccountButton("honzik")
                .closeUpdateConfirm()
                .updateAccountButton("honzik")
                .updateAccount("klara", "olga1", "olga@olga.com", "3")
                .updateConfirm();
    }

    @Test
    public void R5_disableAccount() {
        AccountsPage accounts = Factory.createPO();
        accounts
                .disableAccount("klara");
    }

    @Test
    public void R6_logout() {
        HomePage logout = Factory.createPO();
        logout
                .logoutButton();
    }

    @BeforeClass
    public static void setup() {
        DriverManager.setting_up();
    }

    @AfterClass
    public static void teardown() {
        DriverManager.closeBrowser();
    }

}

package setup;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public abstract class PageObject {
    public DriverManager driverManager = DriverManager.getInstance();

    public PageObject(){
        driverManager.waitForPageToLoad();
        synchronize();
    }

    public abstract void synchronize();

}

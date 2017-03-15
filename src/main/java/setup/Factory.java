package setup;

import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public class Factory {

    @SuppressWarnings("unchecked")
    public static <T extends PageObject> T createPO(T... type){
        return (T) initElements(DriverManager.getWebDriver(), type.getClass().getComponentType());
    }
}

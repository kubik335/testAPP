package setup;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Alexandra Kolpakova on 12.03.2017.
 */
public class Properties {

    private static DesiredCapabilities capabilities;

    public static void setCapabilities(DesiredCapabilities capp){
        capabilities = capp;
    }

    public static DesiredCapabilities getCapabilities() { return capabilities; }

    public static Platform getPlatform(){
        return capabilities.getPlatform();
    }

    public static String getBrowserName(){
        return capabilities.getBrowserName();
    }

    public static String getUrl(){
        return (String) capabilities.getCapability("URL");
    }

}

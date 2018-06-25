package listener;

import setup.DriverManager;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandra Kolpakova on 11.10.2016.
 */

public class TestListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
    }

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Test [" + description.getMethodName() + "] started");
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Test [" + description.getMethodName() + "] finished");
        //DriverManager.getWebDriver().quit();
    }

    @Override
    public void testFailure(Failure failure) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY_hh.mm_SSS");

        new File("results/screenshots").mkdirs();
        FileOutputStream outputStream = new FileOutputStream("results/screenshots/" +
                dateFormat.format(new Date()) + ".png");

        outputStream.write(DriverManager.getWebDriver().getScreenshotAs(OutputType.BYTES));
        outputStream.close();

    }

    @Override
    public void testAssumptionFailure(Failure failure) {
    }

    @Override
    public void testIgnored(Description description) throws Exception {
    }

}

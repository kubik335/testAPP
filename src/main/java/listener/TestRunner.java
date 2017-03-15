package listener;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;


/**
 * Created by Alexandra Kolpakova on 11.10.2016.
 */

public class TestRunner extends BlockJUnit4ClassRunner {

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code aClass}
     *
     * @param aClass
     * @throws InitializationError if the test class is malformed.
     */

    
    public TestRunner(Class<?> aClass) throws InitializationError {
        super(aClass);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(new TestListener());
        super.run(notifier);
    }
}

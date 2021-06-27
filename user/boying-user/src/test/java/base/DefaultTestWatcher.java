package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


public class DefaultTestWatcher implements TestWatcher {

    private ExtentReports extent;

    public DefaultTestWatcher(ExtentReports extent) {
        this.extent = extent;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        ExtentTest test = extent.startTest(context.getDisplayName(), "Test Success");
        // step log
        test.log(LogStatus.PASS, "success");
        flushReports(extent, test);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable e) {
        ExtentTest test = extent.startTest(context.getDisplayName(), "Test Failed");
        // step log
        test.log(LogStatus.FAIL, e);
        flushReports(extent, test);
    }

    private void flushReports(ExtentReports extent, ExtentTest test) {
        extent.endTest(test);
        extent.flush();
    }
}


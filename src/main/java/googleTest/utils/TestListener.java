package googleTest.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        LogInformation.info("----------------------------------");
        LogInformation.info("Started test: " + result.getName());
        LogInformation.info("----------------------------------");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        LogInformation.info("----------------------------------");
        LogInformation.info("Test finished succesfully: " + result.getName());
        LogInformation.info("----------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogInformation.info("----------------------------------");
        LogInformation.info("Failed test: " + result.getName());
        LogInformation.info("----------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}

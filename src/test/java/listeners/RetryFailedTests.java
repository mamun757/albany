package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class RetryFailedTests implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Test(retryAnalyzer = RetryFailedTests.class)
    public boolean retry(ITestResult result){
        if (retryCount < maxRetryCount){
            retryCount++;
            return true;
        }
       return false;
    }



}

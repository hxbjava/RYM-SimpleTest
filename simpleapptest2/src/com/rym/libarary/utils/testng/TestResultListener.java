package com.rym.libarary.utils.testng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.utils.Log;

public class TestResultListener extends TestListenerAdapter{
	
	public void onTestFailure(ITestResult tr)
	{
		super.onTestFailure(tr);
		Log.logInfo(tr.getName()+" Failure");
		saveScreenShot(tr);
	}
	public void onTestSkipped(ITestResult tr)
	{
		super.onTestSkipped(tr);
		Log.logInfo(tr.getName()+" Skipped");
		saveScreenShot(tr);
	}
	public void onTestSuccess(ITestResult tr)
	{
		super.onTestSuccess(tr);
		Log.logInfo(tr.getName()+" Success");
		saveScreenShot(tr);
	}
	public void onTestStart(ITestResult tr)
	{
		super.onTestStart(tr);
		Log.logInfo(tr.getName()+" Start");
		saveScreenShot(tr);
	}
	
	public void onFinish(ITestContext testContext)
	{
		super.onFinish(testContext);
		ArrayList<ITestResult> testsToBeRemoved=new ArrayList<ITestResult>();
		Set<Integer> passedTestIds=new HashSet<Integer>();
		for(ITestResult passedTest : testContext.getPassedTests().getAllResults())
		{
			Log.logInfo("PassedTests :"+passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}
		
		Set<Integer> skipTestIds=new HashSet<Integer>();
		for(ITestResult skipTest : testContext.getPassedTests().getAllResults())
		{
			Log.logInfo("skipTestIds :"+skipTest.getName());
			
			int skipTestId=getId(skipTest);
			
			if(skipTestIds.contains(skipTestId) || passedTestIds.contains(skipTestId)) 
			{
				 testsToBeRemoved.add(skipTest);
			}else
			{
				skipTestIds.add(getId(skipTest));
			}
		}
		
		Set<Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
            Log.logInfo("FailedTest : " + failedTest.getName());
            int failedTestId = getId(failedTest);

            if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId) ||
                    skipTestIds.contains(failedTestId)) {
                testsToBeRemoved.add(failedTest);
            } else {
                failedTestIds.add(failedTestId);
            }
        }
        
        for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext(); ) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {
                Log.logInfo("Remove repeat Fail Test : " + testResult.getName());
                iterator.remove();
            }
        }
		
	}
	
	private int getId(ITestResult result)
	{
		int id=result.getTestClass().getName().hashCode();
		id=id+result.getMethod().getMethodName().hashCode();
		id=id+(result.getParameters()!=null ? Arrays.hashCode(result.getParameters()):0);
		return id;
	}
	
	private void saveScreenShot(ITestResult tr)
	{
		String filePath_testngReports=null;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime=formatter.format(new Date());
		String fileName=tr.getName()+"-" +mDateTime;
		
		try {
			filePath_testngReports=AutoTestBase.ScreenShot(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.logInfo(tr.getName() + " takeScreenshot Failure:");
		}
		
		if(null !=filePath_testngReports && !"".equals(filePath_testngReports))
		{
			Reporter.setCurrentTestResult(tr);
			Reporter.log("<img src=\"../screenShots/" + fileName + ".png" + "\" style=\"width:180px;height:300px;\"/>");
		}
	}
}

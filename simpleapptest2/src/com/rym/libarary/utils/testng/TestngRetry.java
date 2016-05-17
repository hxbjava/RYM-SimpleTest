package com.rym.libarary.utils.testng;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.rym.libarary.utils.Log;

public class TestngRetry implements IRetryAnalyzer{

	private static Logger logger=Logger.getLogger(TestngRetry.class);
	private int retryCount=1;
	private static int maxRetryCount;
	
	static{
		ConfigReader config=ConfigReader.getInstance();
		maxRetryCount=config.getRetryCount();
		Log.logInfo("retrycount=" + maxRetryCount);
		Log.logInfo("sourceCodeDir=" + config.getSourceCodeDir());
		Log.logInfo("sourceCodeEncoding=" + config.getSrouceCodeEncoding());
	}
	
	
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount<=maxRetryCount)
		{
			String message="Retry for [" + result.getName() + "] on class [" + result.getTestClass().getName() + "] Retry "
					+ retryCount + " times";
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}
	
	public static int getMaxRetryCount()
	{
		return maxRetryCount;
	}
	
	public int getRetryCount() {
		return retryCount;
	}
}

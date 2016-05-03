package com.rym.libarary.utils;

import org.apache.log4j.*;
import org.testng.Reporter;

import java.io.File;

public class Log {
	/**
	 * Created by huxuebing on 2016/02/29
	 * 
	 */
	private static Logger logger;
	
	private static String filePath="src/log4j.properties";
	
	static
	{
		logger=Logger.getLogger("dev_log");
		PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
	}
	
	/**
	 * 纪录info级别日志
	 * 
	 * @param message the message object.
	 */
	public static void logInfo(Object message)
	{
		logger.info("[INFO] "+message);
		Reporter.log(new Tools().getSimpleDateFormat()+" : "+"[INFO] "+message);
	}
	
	/**
	 * 纪录测试步骤信息
	 * 
	 * @param message the message object.
	 */
	public static void logStep(Object message)
	{
		logger.info("[STEP] "+message);
		Reporter.log(new Tools().getSimpleDateFormat()+" : "+"[STEP] "+message);
	}
	
	/**
	 * 纪录测试流日志
	 * 
	 * @param message the message object.
	 */
	public static void logFlow(Object message)
	{
		logger.info("[FLOW] "+message);
		Reporter.log(new Tools().getSimpleDateFormat()+" : "+"[FLOW] "+message);
	}
	
	/**
	 * 纪录Error级别日志
	 * 
	 * @param message the message object.
	 */
	public static void logError(Object message)
	{
		logger.error("[ERROR] "+message);
		Reporter.log(new Tools().getSimpleDateFormat()+" : "+"[ERROR] "+message);
	}
	
	/**
	 * 纪录Warn级别日志
	 * 
	 * @param message the message object.
	 */
	public static void logWarn(Object message)
	{
		logger.warn("[WARN] "+message);
		Reporter.log(new Tools().getSimpleDateFormat()+" : "+"[WARN] "+message);
	}
}

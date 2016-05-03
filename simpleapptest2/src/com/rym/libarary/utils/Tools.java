package com.rym.libarary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	/**
	 * Created by huxuebing on 2016/02/29
	 */
	
	
	/**
	 * 返回当前时间，格式为2016-02-09 14:58:30
	 */
	public static String getSimpleDateFormat()
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
}

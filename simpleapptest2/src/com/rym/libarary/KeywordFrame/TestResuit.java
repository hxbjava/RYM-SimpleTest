package com.rym.libarary.KeywordFrame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.rym.libarary.base.AutoTestBase;

public class TestResuit extends AutoTestBase{

	public static void WriteTestResuit() throws FileNotFoundException
	{
		int failCount=0;
		int suncessCount=0;
		StringBuilder sb = new StringBuilder(); 
		PrintStream printStream= new PrintStream(new FileOutputStream( 
			"test-output/html/report.html"));

		sb.append("<html>");
		sb.append("<head>"); 
		sb.append("<title>测试结果</title>"); 
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"); 
		sb.append("</head>"); 
		sb.append("<body bgcolor=\"#FFF8DC\">"); 
		sb.append("<div align=\"center\">"); 
		for (String string : listResult) {
             if(string.contains("失败"))
             {
            	 failCount=failCount+1;
             }else if(string.contains("成功"))
             {
            	 suncessCount=suncessCount+1;
             }
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		sb.append("<td>"); 
		sb.append("2016Q3自动化测试报告                            "+df.format(new Date()));
		sb.append("</td>"); 
		sb.append("<br/>");
		sb.append("<td>");  
		sb.append("<font color=\"red\">"); 
		sb.append("测试案例共计: "+(failCount+suncessCount)+" 个-----"); 
		sb.append("测试成功案例共计: "+suncessCount+" 个-----"); 
		sb.append("测试失败案例共计: "+failCount+" 个"); 
		sb.append("</font>"); 
		sb.append("</td>"); 
		sb.append("<br/>");
		sb.append("<br/>");
		for (String string : listResult) {
			 sb.append("<td>");  
			sb.append(string); 
            sb.append("</td>"); 
            sb.append("<br/>");
		}
		sb.append("<br/>"); 
		sb.append("</div></body></html>");  
        printStream.println(sb.toString()); 
	}
	
}

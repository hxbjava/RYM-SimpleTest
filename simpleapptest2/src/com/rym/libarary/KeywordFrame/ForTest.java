package com.rym.libarary.KeywordFrame;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class ForTest extends AutoTestBase{

	//当前执行案例名称
	public static String currentCase=null;
	//已经执行过的案例名称集合
	public static List<String> executedCase=null;
	//当前执行下标
	public static int listID=0;
	//案例执行下标
	public static int caseID=0;
	//案例执行状态
	public static int runType=0;
	//获取测试案例集合
	public static List<List<String>> list=ReadExcel2.readXls(excelPath,0);
	//获取公共测试案例集合
	public static List<List<String>> listPublic=ReadExcel2.readXls(excelPath,2);
	
	@Test(groups = { "p5" })
	public static void forTest() {
		
		if(FailType>0)
		{
			//如果该用例失败超过3次
			if(FailType==3)
			{
				//写执行失败数据
				Log.logInfo("案例:"+currentCase+"执行失败！");
				listResult.add("案例:"+currentCase+"执行失败！");
				//检测是否是最后一条用例失败3次以上，如果是就将runtype改为1；
				if(listID+1==list.size())
				{
					runType=1;
				}else
				{
					listID=listID+1;
					FailType=0;
					for (; listID < list.size(); listID++) {
						
						List<String> list2 =list.get(listID);
						if(list2.get(0)!=null)
						{
							//跑下一个用例
							caseID=listID;
							Log.logInfo("currentCase:"+currentCase+"当前案例名称:"+list2.get(0));
							currentCase=list2.get(0);
							break;
						}
					}
				}
			}else
			{
				//失败重跑中
				listID=caseID;
			}
		}
		
		for (; listID < list.size(); listID++) {
			
		List<String> list2 =list.get(listID);
		if(runType==1)
		{
			Log.logInfo("案例全部执行完毕！");
			listResult.add("案例全部执行完毕！");
			break;
		}
			//当前案例名称为空
			if(currentCase==null)
			{
				//将案例执行下标变为当前执行下标
				caseID=listID;
				//改变当前案例的值
				currentCase=list2.get(0);
				
			}else//当前案例名称不为空
			{
				//如果获取到的案例名称为空
				if(list2.get(0)!=null)
				{
					//如果获取到的案例名称与当前案例名称不一样，关掉app初始化环境，然后执行下一个用例
					if(!list2.get(0).equals(currentCase))
					{
						//将案例执行下标变为当前执行下标
						caseID=listID;
						
						//写执行成功数据
						Log.logInfo("案例:"+currentCase+"执行成功！");
						listResult.add("案例:"+currentCase+"执行成功！");
						//改变当前案例的值
						currentCase=list2.get(0);
						
						((AppiumDriver)driver).closeApp();
						Sleep.sleep(3);
						((AppiumDriver)driver).launchApp();
						Login.startApp();
						Sleep.sleep(3);
					}
				}
			}
			System.out.println(list2);
			ReadApi.readApi(list2);
			if(listID+1==list.size())
			{
				//写执行成功数据
				Log.logInfo("案例:"+currentCase+"执行成功！所有案例已跑完");
				listResult.add("案例:"+currentCase+"执行成功！所有案例已跑完");
			}
		}

	}
	
	
	public static void CallCase(String caseName)
	{
		int listpublicType=0;
		for (List<String> listpublic : listPublic) {
			
			if(listpublicType==1)
			{
//				Log.logInfo("listpublicType等于1");
				if(listpublic.get(0)==null)
				{
//					Log.logInfo("等于null");
					System.out.println(listpublic);
					ReadApi.readApi(listpublic);
				}else
				{
//					Log.logInfo("不等于null");
					break;
				}
			}else{
//				Log.logInfo("listpublicType等于0");
				if(listpublic.get(0)!=null)
				{
	//				Log.logInfo(listpublic.get(0));
					if(listpublic.get(0).equals(caseName))
					{
						listpublicType=1;
						System.out.println(listpublic);
						ReadApi.readApi(listpublic);
//						Log.logInfo("listpublicType改成1-－－"+listpublicType);
					}
				}
			}
		}
		
	}
	
	
	public static void forTest2() {
		
		if(FailType>0)
		{
			//失败重跑中
			listID=caseID;
			//如果该用例失败超过3次
			if(FailType==3)
			{
				Log.logInfo("案例:"+currentCase+"执行失败！");
				listID=listID+1;
				FailType=0;
				for (; listID < list.size(); listID++) {
					
					List<String> list2 =list.get(listID);
					if(list2.get(0)!=null)
					{
						//跑下一个用例
						caseID=listID;
					}
				}
			}
		}
		
		for (; listID < list.size(); listID++) {
			
		List<String> list2 =list.get(listID);
			
			//当前案例名称为空
			if(currentCase==null)
			{
				//将案例执行下标变为当前执行下标
				caseID=listID;
				//改变当前案例的值
				currentCase=list2.get(0);
				
				System.out.println(list2);
				ReadApi.readApi(list2);
			}else//当前案例名称不为空
			{
				//如果获取到的案例名称为空
				if(list2.get(0)==null)
				{
					System.out.println(list2);
					ReadApi.readApi(list2);
				}else//如果获取到的案例名称不为空
				{
					//如果获取到的案例名称与当前案例名称不一样，关掉app初始化环境，然后执行下一个用例
					if(!list2.get(0).equals(currentCase))
					{
						//将案例执行下标变为当前执行下标
						caseID=listID;
//						//将之前的案例名称放入到执行过的案例名称集合里;
//						executedCase.add(currentCase);
						
						Log.logInfo("案例:"+currentCase+"执行成功！");
						//改变当前案例的值
						currentCase=list2.get(0);
						
						((AppiumDriver)driver).closeApp();
						Sleep.sleep(3);
						((AppiumDriver)driver).launchApp();
						Login.startApp();
						Sleep.sleep(3);
						
						System.out.println(list2);
						ReadApi.readApi(list2);
					}else//如果获取到的案例名称与当前案例名称一样，失败重跑中
					{
						System.out.println(list2);
						ReadApi.readApi(list2);
					}
				}
			}
			
		}
	}
}

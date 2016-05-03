package com.rym.libarary.utils;

public class Sleep {
	public static void sleep(long time)
    {
   	 try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

package com.xrk.uiac.sdk.test.business;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;

public class ResetPassword
{
	private static ExecutorService bgThread = Executors.newFixedThreadPool(30);
	private static UiacClient client = UiacClient.Create("http://192.168.6.190:8081", "1003", "1.0");
	
	private static AtomicLong atomUid = new AtomicLong(0);
	
	public static void main(String[] args)
	{
		final long maxUid = 2000000;
		
		for (int i=3000000; i<3200000; i++)
		{
			resetPassword(i, "xrktest");
		}
	}
	
	private static void resetPassword(long uid, String password)
	{
		bgThread.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					client.account().resetPassword(uid, password, true);
				}
				catch (UiacException e)
				{
					System.out.println(uid + " " + e.getErrCode());
				}
			}
		});
	}
}

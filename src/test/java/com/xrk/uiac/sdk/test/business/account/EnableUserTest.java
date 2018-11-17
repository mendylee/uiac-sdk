package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class EnableUserTest extends BaseBusinessTest
{
	public EnableUserTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void enableUserMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			enableUserMultiThread(threadCount, mobile);
		}
	}
	
	public void enableUserMultiThread(int threadCount, String mobile)
	{
		long uid = login(mobile, "123456");
		
		if (uid == 0)
		{
			return;
		}
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					enableUser(uid);
				}
			});
		}
	}
	
	public void enableUser(long uid)
	{
		try 
		{
			client.account().enableUser(adminUid, uid, "");
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to enable user: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

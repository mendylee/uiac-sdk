package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class DisableUserTest extends BaseBusinessTest
{
	public DisableUserTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void disableUserMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			disableUserMultiThread(threadCount, mobile);
		}
	}
	
	public void disableUserMultiThread(int threadCount, String mobile)
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
					disableUser(uid);
				}
			});
		}
	}
	
	public void disableUser(long uid)
	{
		try 
		{
			client.account().disableUser(adminUid, uid, "");
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to disable user: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

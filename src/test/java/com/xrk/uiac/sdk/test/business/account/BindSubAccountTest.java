package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class BindSubAccountTest extends BaseBusinessTest
{
	public BindSubAccountTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void bindSubAccountMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			bindSubAccountMultiThread(threadCount, mobile);
		}
	}
	
	public void bindSubAccountMultiThread(int threadCount, String mobile)
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
					bindSubAccount(uid);
				}
			});
		}
	}
	
	public void bindSubAccount(long uid)
	{
		try 
		{
			client.account().bindSubAccount(uid, 0, "2008", "");
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to bind subAccount: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

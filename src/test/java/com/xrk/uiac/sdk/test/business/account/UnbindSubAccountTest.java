package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class UnbindSubAccountTest extends BaseBusinessTest
{
	public UnbindSubAccountTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void unbindSubAccountMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			unbindSubAccountMultiThread(threadCount, mobile);
		}
	}
	
	public void unbindSubAccountMultiThread(int threadCount, String mobile)
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
					unbindSubAccount(uid);
				}
			});
		}
	}
	
	public void unbindSubAccount(long uid)
	{
		try 
		{
			client.account().unBindSubAccount(uid, 2008, "","");
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to unbind subAccount: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

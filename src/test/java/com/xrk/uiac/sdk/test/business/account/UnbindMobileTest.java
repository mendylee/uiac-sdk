package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class UnbindMobileTest extends BaseBusinessTest
{
	public UnbindMobileTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void unbindMobileMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			unbindMobileMultiThread(threadCount, mobile);
		}
	}
	
	public void unbindMobileMultiThread(int threadCount, String mobile)
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
					unbindMobile(uid);
				}
			});
		}
	}
	
	public void unbindMobile(long uid)
	{
		try 
		{
			client.account().unBindMobile(uid, "", true);
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to unbind mobile: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

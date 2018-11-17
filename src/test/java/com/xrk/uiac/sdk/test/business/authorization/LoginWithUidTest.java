package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class LoginWithUidTest extends BaseBusinessTest
{
	public LoginWithUidTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void loginWithUidMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			loginWithUidMultiThread(threadCount, mobile);   
		}
	}
	
	public void loginWithUidMultiThread(int threadCount, String mobile)
	{
		long uid = login(mobile, "123456");
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					loginWithUid(uid);
				}
			});
		}
	}
	
	public void loginWithUid(long uid)
	{
		try 
		{
			client.authorization().loginUid(String.valueOf(uid), "123456");
		}
		catch (UiacException e) 
		{
			System.out.println("fail to login " + e.getErrCode() + " " + e.getMessage());
		}
	}
}

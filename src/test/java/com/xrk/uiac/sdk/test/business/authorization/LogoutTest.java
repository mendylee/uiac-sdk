package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class LogoutTest extends BaseBusinessTest
{
	public LogoutTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void logoutMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			logoutMultiThread(threadCount, mobile);   
		}
	}
	
	public void logoutMultiThread(int threadCount, String mobile)
	{
		UserAuthorizationEntity uae = null;
		
		try
		{
			uae = client.authorization().login(mobile, "123456");
		}
		catch (UiacException e)
		{
			e.printStackTrace();
			return;
		}
		
		String token = uae.getAccessToken();
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					logout(token);
				}
			});
		}
	}
	
	public void logout(String token)
	{
		try 
		{
			client.authorization().logout(token);
		}
		catch (UiacException e) 
		{
			System.out.println("fail to logout " + e.getErrCode() + " " + e.getMessage());
		}
	}
}

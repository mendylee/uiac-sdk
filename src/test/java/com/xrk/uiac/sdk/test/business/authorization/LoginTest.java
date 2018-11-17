package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class LoginTest extends BaseBusinessTest
{
	public LoginTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void loginMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			loginMultiThread(threadCount, mobile);   
		}
	}
	
	public void loginMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					login(mobile);
				}
			});
		}
	}
	
	public UserAuthorizationEntity login(String mobile)
	{
		try 
		{
			UserAuthorizationEntity uae = client.authorization().login(mobile, "123456");
			return uae;
		}
		catch (UiacException e) 
		{
			System.out.println("fail to login " + e.getErrCode() + " " + e.getMessage());
			return null;
		}
	}
}

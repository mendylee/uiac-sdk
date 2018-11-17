package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class VerifyTokenTest extends BaseBusinessTest
{
	public VerifyTokenTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void verifyTokenMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			verifyTokenMultiThread(threadCount, mobile);   
		}
	}
	
	public void verifyTokenMultiThread(int threadCount, String mobile)
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
					verifyToken(token);
				}
			});
		}
	}
	
	public void verifyToken(String token)
	{
		try 
		{
			client.authorization().verify(token);
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to verify token " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

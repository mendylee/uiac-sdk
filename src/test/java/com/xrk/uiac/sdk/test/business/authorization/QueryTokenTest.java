package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class QueryTokenTest extends BaseBusinessTest
{
	public QueryTokenTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void queryTokenMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			queryTokenMultiThread(threadCount, mobile);   
		}
	}
	
	public void queryTokenMultiThread(int threadCount, String mobile)
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
		long uid = uae.getUid();
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					queryToken(token, uid);
				}
			});
		}
	}
	
	public void queryToken(String token, long uid)
	{
		try 
		{
			client.authorization().query(uid, token);
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to query token " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

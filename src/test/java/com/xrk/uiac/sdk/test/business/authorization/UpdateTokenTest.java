package com.xrk.uiac.sdk.test.business.authorization;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class UpdateTokenTest extends BaseBusinessTest
{
	public UpdateTokenTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void updateTokenMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			updateTokenMultiThread(threadCount, mobile);   
		}
	}
	
	public void updateTokenMultiThread(int threadCount, String mobile)
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
		
		long uid = uae.getUid();
		String token = uae.getAccessToken();
		String refreshToken = uae.getRefreshToken();
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					updateToken(uid, token, refreshToken);
				}
			});
		}
	}
	
	public UserAuthorizationEntity updateToken(long uid, String token, String refreshToken)
	{
		try 
		{
			UserAuthorizationEntity uae = client.authorization().updateToken(uid, token, refreshToken);
			return uae;
		}
		catch (UiacException e) 
		{
			System.out.println("fail to updateToken " + e.getErrCode() + " " + e.getMessage());
			return null;
		}
	}
}

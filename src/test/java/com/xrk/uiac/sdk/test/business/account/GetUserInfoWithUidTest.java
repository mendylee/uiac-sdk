package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class GetUserInfoWithUidTest extends BaseBusinessTest
{
	public GetUserInfoWithUidTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void getUserInfoWithUidMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			getUserInfoWithUidMultiThread(threadCount, mobile);
		}
	}
	
	public void getUserInfoWithUidMultiThread(int threadCount, String mobile)
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
					getUserInfoWithUid(uid);
				}
			});
		}
	}
	
	public void getUserInfoWithUid(long uid)
	{
		try 
		{
			client.account().getUserInfo(uid, "");
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to get user info with uid: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

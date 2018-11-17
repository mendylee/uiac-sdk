package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class GetUserInfoWithAccountTest extends BaseBusinessTest
{
	public GetUserInfoWithAccountTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void getUserInfoWithAccountMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			getUserInfoWithAccountMultiThread(threadCount, mobile);
		}
	}
	
	public void getUserInfoWithAccountMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					getUserInfoWithAccount(mobile);
				}
			});
		}
	}
	
	public void getUserInfoWithAccount(String mobile)
	{
		try
		{
			client.account().getUserInfo(mobile, "");
		}
		catch (UiacException e)
		{
			System.out.println("fail to get userinfo, " + " mobile: " + mobile + " " + e.getErrCode() + " " + e.getMessage());
		}
	}
}

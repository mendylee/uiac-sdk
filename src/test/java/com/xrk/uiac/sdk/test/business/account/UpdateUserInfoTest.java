package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class UpdateUserInfoTest extends BaseBusinessTest
{
	public UpdateUserInfoTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void updateUserInfoMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			updateUserInfoMultiThread(threadCount, mobile);
		}
	}
	
	public void updateUserInfoMultiThread(int threadCount, String mobile)
	{
		final long uid = login(mobile, "123456");
		
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					updateUserInfo(uid);
				}
			});
		}
	}
	
	public void updateUserInfo(long uid)
	{
		try
		{
			client.account().updateUserInfo(uid, "", "{\"qq\":\"3423333\"}", "{\"d\":\"ab\"}");
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to update");
				e.printStackTrace();
			}
		}
	}
}

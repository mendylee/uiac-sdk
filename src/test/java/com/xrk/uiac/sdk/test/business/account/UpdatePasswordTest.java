package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class UpdatePasswordTest extends BaseBusinessTest
{
	public UpdatePasswordTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void updatePasswordMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			updatePasswordMultiThread(threadCount, mobile);
		}
	}
	
	public void updatePasswordMultiThread(int threadCount, String mobile)
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
					updatePassword(uid);
				}
			});
		}
	}
	
	public void updatePassword(long uid)
	{
		try 
		{
			client.account().updatePassword(uid, "", "123456", "123456", true);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to update password: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

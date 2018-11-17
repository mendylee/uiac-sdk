package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class BindMobileTest extends BaseBusinessTest
{
	public BindMobileTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void bindSubAccountMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			bindMobileMultiThread(threadCount, startMobile + i);
		}
	}
	
	public void bindMobileMultiThread(int threadCount, long mobile)
	{
		long uid = login(String.valueOf(mobile), "123456");
		
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
					bindMobile(uid, mobile);
				}
			});
		}
	}
	
	public void bindMobile(long uid, long mobile)
	{
		processBindMobile(uid, mobile);
		reset(uid, mobile);
	}
	
	private void processBindMobile(long uid, long mobile)
	{
		try
		{
			client.account().bindMobile(uid, String.valueOf(mobile - 2000000000l), "", true);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to bind mobile, mobile " + mobile);
			}
		}
	}
	
	private void reset(long uid, long mobile)
	{
		try
		{
			client.account().bindMobile(uid, String.valueOf(mobile), "", true);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to reset mobile, mobile " + mobile);
			}
		}
	}
}

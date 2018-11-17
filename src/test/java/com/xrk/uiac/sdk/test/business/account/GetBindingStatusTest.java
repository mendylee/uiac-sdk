package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class GetBindingStatusTest extends BaseBusinessTest
{
	public GetBindingStatusTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void getBindingStatusMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			getBindingStatusMultiThread(threadCount, mobile);
		}
	}
	
	public void getBindingStatusMultiThread(int threadCount, String mobile)
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
					getBindingStatus(uid);
				}
			});
		}
	}
	
	public void getBindingStatus(long uid)
	{
		try 
		{
			client.account().getBindingStatus(uid, "");
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to get binding status: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

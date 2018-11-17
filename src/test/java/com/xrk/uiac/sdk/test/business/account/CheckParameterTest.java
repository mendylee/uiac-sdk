package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class CheckParameterTest extends BaseBusinessTest
{
	public CheckParameterTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void checkParameterMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			checkParameterMultiThread(threadCount, mobile);   
		}
	}
	
	public void checkParameterMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					checkParameter(mobile);
				}
			});
		}
	}
	
	public void checkParameter(String mobile)
	{
		try 
		{
			client.account().checkParameter(mobile);
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to check parameter " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

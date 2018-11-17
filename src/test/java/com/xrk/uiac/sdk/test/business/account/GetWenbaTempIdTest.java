package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class GetWenbaTempIdTest extends BaseBusinessTest
{
	public GetWenbaTempIdTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void getWenbaTempIdMultiThreadMultiMobile(int threadCount, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			getWenbaTempIdMultiThread(threadCount);
		}
	}
	
	public void getWenbaTempIdMultiThread(int threadCount)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					getWenbaTempId();
				}
			});
		}
	}
	
	public void getWenbaTempId()
	{
		try
		{
			client.account().getWenbaTempId();
		}
		catch (UiacException e)
		{
			System.out.println("fail to get tempId");
		}
	}
}

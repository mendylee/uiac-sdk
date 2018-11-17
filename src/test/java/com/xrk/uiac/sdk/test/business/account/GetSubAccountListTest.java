package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class GetSubAccountListTest extends BaseBusinessTest
{
	public GetSubAccountListTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void getSubAccountListMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			getSubAccountListMultiThread(threadCount, mobile);
		}
	}
	
	public void getSubAccountListMultiThread(int threadCount, String mobile)
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
					getSubAccountList(uid);
				}
			});
		}
	}
	
	public void getSubAccountList(long uid)
	{
		try 
		{
			client.account().getSubAccountList(uid, "");
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to get subAccount list: " + e.getErrCode() + " " + uid);
				e.printStackTrace();
			}
		}
	}
}

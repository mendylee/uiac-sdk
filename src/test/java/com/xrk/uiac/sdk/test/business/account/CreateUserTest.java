package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class CreateUserTest extends BaseBusinessTest
{
	public CreateUserTest(UiacClient client, ExecutorService bgThread) 
	{
		super(client, bgThread);
	}

	public void createUserMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			createUserMultiThread(threadCount, mobile);   
		}
	}
	
	public void createUserMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{					
					createUser(mobile);
				}
			});
		}
	}
	
	public void createUser(String mobile)
	{
		try 
		{
			client.account().createUser(mobile, "123456", "{\"address\":\"试试看中文\"}", "", true);
			System.out.println("***********注册成功： " + mobile);
		}
		catch (UiacException e) 
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

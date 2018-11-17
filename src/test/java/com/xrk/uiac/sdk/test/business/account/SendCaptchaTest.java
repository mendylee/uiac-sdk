package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class SendCaptchaTest extends BaseBusinessTest
{
	public SendCaptchaTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void sendCaptchaMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			sendCaptchaMultiThread(threadCount, mobile);
		}
	}
	
	public void sendCaptchaMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					sendCaptcha(mobile);
				}
			});
		}
	}
	
	public void sendCaptcha(String mobile)
	{
		try
		{
			client.account().sendCaptcha(mobile, 1);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to send captcha, " + " mobile: " + mobile + " " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

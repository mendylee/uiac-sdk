package com.xrk.uiac.sdk.test.business.account;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.BaseBusinessTest;

public class ValidateCaptchaTest extends BaseBusinessTest
{
	public ValidateCaptchaTest(UiacClient client, ExecutorService bgThread)
	{
		super(client, bgThread);
	}
	
	public void validateCaptchaMultiThreadMultiMobile(int threadCount, long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			validateCaptchaMultiThread(threadCount, mobile);
		}
	}
	
	public void validateCaptchaMultiThread(int threadCount, String mobile)
	{
		for (int i=0; i<threadCount; i++)
		{
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					validateCaptcha(mobile);
				}
			});
		}
	}
	
	public void validateCaptcha(String mobile)
	{
		try
		{
			client.account().validateCaptcha(mobile, "111111", 1);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to validate captcha, " + " mobile: " + mobile + " " + e.getErrCode() + " " + e.getMessage());
			}
		}
	}
}

package com.xrk.uiac.sdk.test.business;

import java.util.concurrent.ExecutorService;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;

public class BaseBusinessTest
{
	protected UiacClient client = null;
	
	protected ExecutorService bgThread = null;
	
	protected long adminUid = 0;
	
	public BaseBusinessTest(UiacClient client, ExecutorService bgThread)
	{
		this.client = client;
		this.bgThread = bgThread;
		
		loginAdmin();
	}
	
	public long login(String mobile, String password)
	{
		long uid = 0;
		
		try
		{
			UserAuthorizationEntity uae = client.authorization().login(mobile, password);
			uid = uae.getUid();
		}
		catch (Exception e)
		{
			System.out.println("fail to login");
		}
		
		return uid;
	}
	
	private long loginAdmin()
	{
		try
		{
			client.account().createUser("17900000000", "123456", "", "", true);
		}
		catch (UiacException e)
		{
			if (e.getErrCode().equals("-1"))
			{
				System.out.println("fail to create administrator");
				e.printStackTrace();
			}
		}
		
		try
		{
			UserAuthorizationEntity uae = client.authorization().login("17900000000", "123456");
			adminUid = uae.getUid();
		}
		catch (Exception e)
		{
			System.out.println("fail to login administrator");
		}
		
		return adminUid;
	}
}

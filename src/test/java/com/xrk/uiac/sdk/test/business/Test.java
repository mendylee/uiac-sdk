package com.xrk.uiac.sdk.test.business;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.CreateUserResponse;
import com.xrk.uiac.sdk.entity.GetUserInfoResponse;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.test.business.account.BindMobileTest;
import com.xrk.uiac.sdk.test.business.account.BindSubAccountTest;
import com.xrk.uiac.sdk.test.business.account.CheckParameterTest;
import com.xrk.uiac.sdk.test.business.account.CreateUserTest;
import com.xrk.uiac.sdk.test.business.account.DisableUserTest;
import com.xrk.uiac.sdk.test.business.account.EnableUserTest;
import com.xrk.uiac.sdk.test.business.account.GetBindingStatusTest;
import com.xrk.uiac.sdk.test.business.account.GetSubAccountListTest;
import com.xrk.uiac.sdk.test.business.account.GetUserInfoWithAccountTest;
import com.xrk.uiac.sdk.test.business.account.GetUserInfoWithUidTest;
import com.xrk.uiac.sdk.test.business.account.GetUserStatusTest;
import com.xrk.uiac.sdk.test.business.account.GetWenbaTempIdTest;
import com.xrk.uiac.sdk.test.business.account.ResetPasswordTest;
import com.xrk.uiac.sdk.test.business.account.SendCaptchaTest;
import com.xrk.uiac.sdk.test.business.account.UnbindMobileTest;
import com.xrk.uiac.sdk.test.business.account.UnbindSubAccountTest;
import com.xrk.uiac.sdk.test.business.account.UpdatePasswordTest;
import com.xrk.uiac.sdk.test.business.account.UpdateUserInfoTest;
import com.xrk.uiac.sdk.test.business.account.ValidateCaptchaTest;
import com.xrk.uiac.sdk.test.business.authorization.LoginTest;
import com.xrk.uiac.sdk.test.business.authorization.LoginWithUidTest;
import com.xrk.uiac.sdk.test.business.authorization.LogoutTest;
import com.xrk.uiac.sdk.test.business.authorization.QueryTokenTest;
import com.xrk.uiac.sdk.test.business.authorization.UpdateTokenTest;
import com.xrk.uiac.sdk.test.business.authorization.VerifyTokenTest;

public class Test
{
	private static ExecutorService bgThread = Executors.newFixedThreadPool(50);
	private static UiacClient client = UiacClient.Create("http://192.168.6.190:8081", "2008", "1.0");
	
	private static CreateUserTest createUser = null;
	private static GetUserInfoWithAccountTest getUserInfoWithAccount = null;
	private static GetUserInfoWithUidTest getUserInfoWithUid = null;
	private static UpdateUserInfoTest updateUserInfo = null;
	
	private static UpdatePasswordTest updatePassword = null;
	private static ResetPasswordTest resetPassword = null;
	
	private static BindSubAccountTest bindSubAccount = null;
	private static UnbindSubAccountTest unbindSubAccount = null;
	private static GetSubAccountListTest getSubAccountList = null;
	
	private static BindMobileTest bindMobile = null;
	private static UnbindMobileTest unbindMobile = null;
	private static GetBindingStatusTest getBindingStatus = null;
	
	private static DisableUserTest disableUser = null;
	private static EnableUserTest enableUser = null;
	private static GetUserStatusTest getUserStatus = null;
	
	private static SendCaptchaTest sendCaptcha = null;
	private static ValidateCaptchaTest validateCaptcha = null;
	
	private static GetWenbaTempIdTest getWenbaTempId = null;
	private static CheckParameterTest checkParameter = null;
	
	private static LoginTest login = null;
	private static LoginWithUidTest loginWithUid = null;
	private static LogoutTest logout = null;
	private static QueryTokenTest queryToken = null;
	private static UpdateTokenTest updateToken = null;
	private static VerifyTokenTest verifyToken = null;
	
	public static void main(String[] args)
	{
		//初始化各个测试实例
		initTestClient();
		
		//System.out.println("start at: " + new Date());
		
		intergrationTest(16000131000l, 10000);
		
		//redisTest(16101000000l, 100000);
		
		//16100250000 ~ 16100850000
		//getUserInfoTest(16100750000l, 100000);
		
		//rollbackUser(16400000000l, 1);
		
//		try
//		{
//			client.account().resetPassword(1432160, "771845", true);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	public static void rollbackUser(long startMobile, int mobileCount)
	{
		final String password = "123456";
		
		//首先批量注册
		System.out.println("create user start");
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			try
			{
				client.account().createUser(mobile, password, "", "", true);
				System.out.println("create user !!! " + mobile);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//批量回滚
		System.out.println("rollback start");
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			try
			{
				boolean ret = client.account().rollbackUser(mobile, password);
				if (!ret)
				{
					System.out.println("fail to rollback, " + mobile);
				}
				else
				{
					System.out.println("rollback !!!, " + mobile);
				}
			}
			catch (UiacException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void resetPassword(String[] mobiles)
	{
		for (String mobile : mobiles)
		{
			try
			{
				GetUserInfoResponse guir = client.account().getUserInfo(mobile, "");
				client.account().resetPassword(guir.getUid(), "xrktest", true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void createUsers(String[] mobiles)
	{
		for (String mobile : mobiles)
		{
			try
			{
				client.account().createUser(mobile, "xrktest", "", "", true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void getUserInfoTest(long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						GetUserInfoResponse guir = client.account().getUserInfo(mobile, "");
						if (!guir.getUserInfo().getAccount().equals(mobile))
						{
							System.out.println(String.format("account error, account: %s, userinfo.getAccount(): %s", mobile, guir.getUserInfo().getAccount()));
						} 
						if (guir.getUid() != guir.getUserInfo().getUid())
						{
							System.out.println(String.format("uid error, uid: %s, userinfo.getUid(): %s", guir.getUid(), guir.getUserInfo().getUid()));
						}
						
						if (Long.valueOf(mobile).longValue() % 1000 == 0)
						{
							System.out.println("!!!!!! mobile: " + mobile);
						}
					}
					catch (UiacException e)
					{
						System.out.println("get user info error, " + e.getErrCode());
					}
				}
			});
		}
	}
	
	public static void redisTest(long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			String mobile = String.valueOf(startMobile + i);
			
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					long uid = 0;
					
					try
					{
						CreateUserResponse cur = client.account().createUser(mobile, "123456", "", "", true);
						uid = cur.getUid();
					}
					catch (UiacException e)
					{
						System.out.println("create user error, " + e.getErrCode());
						return;
					}
					
					try 
					{
						GetUserInfoResponse guir = client.account().getUserInfo(mobile, "");
						if (uid != guir.getUid())
						{
							System.out.println(String.format("error!! uid is not equal: %d %d", uid, guir.getUid()));
						}
						else if (!mobile.equals(guir.getUserInfo().getAccount()))
						{
							System.out.println(String.format("error!! mobile is not equal: %s %s", mobile, guir.getUserInfo().getAccount()));
						}
						else
						{
							if (Long.valueOf(mobile).longValue() % 1000 == 0)
							{
								System.out.println("success!! " + mobile);
							}
						}
					}
					catch (UiacException e)
					{
						System.out.println("get user info error, " + e.getErrCode());
						return;
					}
				}	
			});
			
		}
	}
	
	public static void intergrationTest(long startMobile, int mobileCount)
	{
		for (int i=0; i<mobileCount; i++)
		{
			long mobileLong = startMobile + i;
			String mobile = String.valueOf(startMobile + i);
			
			bgThread.execute(new Runnable()
			{
				@Override
				public void run()
				{
					System.out.println("mobile: " + mobile);
					
					createUser.createUser(mobile);
					
					UserAuthorizationEntity uae = login.login(mobile);
					long uid = uae.getUid();
					String token = uae.getAccessToken();
					String refreshToken = uae.getRefreshToken();
					
					queryToken.queryToken(refreshToken, uid);
					verifyToken.verifyToken(token);
					uae = updateToken.updateToken(uid, token, refreshToken);
					token = uae.getAccessToken();
					logout.logout(token);
					loginWithUid.loginWithUid(uid);
					
					uae = login.login(mobile);
					token = uae.getAccessToken();
					refreshToken = uae.getRefreshToken();
					
					getUserInfoWithAccount.getUserInfoWithAccount(mobile);
					getUserInfoWithUid.getUserInfoWithUid(uid);
					updateUserInfo.updateUserInfo(uid);
					
					updatePassword.updatePassword(uid);
					resetPassword.resetPassword(uid);
					
					bindSubAccount.bindSubAccount(uid);
					unbindSubAccount.unbindSubAccount(uid);
					getSubAccountList.getSubAccountList(uid);
					
					//bindMobile.bindMobile(uid, mobileLong);
					unbindMobile.unbindMobile(uid);
					getBindingStatus.getBindingStatus(uid);
					
					//disableUser.disableUser(uid);
					//enableUser.enableUser(uid);
					getUserStatus.getUserStatus(uid);
					
					//sendCaptcha.sendCaptcha(mobile);
					//validateCaptcha.validateCaptcha(mobile);
					
					//getWenbaTempId.getWenbaTempId();
					checkParameter.checkParameter(mobile);
				}
			});
		}
	}
	
	public static void initTestClient()
	{
		createUser = new CreateUserTest(client, bgThread);
		getUserInfoWithAccount = new GetUserInfoWithAccountTest(client, bgThread);
		getUserInfoWithUid = new GetUserInfoWithUidTest(client, bgThread);
		updateUserInfo = new UpdateUserInfoTest(client, bgThread);
		
		updatePassword = new UpdatePasswordTest(client, bgThread);
		resetPassword = new ResetPasswordTest(client, bgThread);
		
		bindSubAccount = new BindSubAccountTest(client, bgThread);
		unbindSubAccount = new UnbindSubAccountTest(client, bgThread);
		getSubAccountList = new GetSubAccountListTest(client, bgThread);
		
		bindMobile = new BindMobileTest(client, bgThread);
		unbindMobile = new UnbindMobileTest(client, bgThread);
		getBindingStatus = new GetBindingStatusTest(client, bgThread);
		
		disableUser = new DisableUserTest(client, bgThread);
		enableUser = new EnableUserTest(client, bgThread);
		getUserStatus = new GetUserStatusTest(client, bgThread);
		
		sendCaptcha = new SendCaptchaTest(client, bgThread);
		validateCaptcha = new ValidateCaptchaTest(client, bgThread);
		
		getWenbaTempId = new GetWenbaTempIdTest(client, bgThread);
		checkParameter = new CheckParameterTest(client, bgThread);
		
		login = new LoginTest(client, bgThread);
		loginWithUid = new LoginWithUidTest(client, bgThread);
		logout = new LogoutTest(client, bgThread);
		queryToken = new QueryTokenTest(client, bgThread);
		updateToken = new UpdateTokenTest(client, bgThread);
		verifyToken = new VerifyTokenTest(client, bgThread);
	}
}

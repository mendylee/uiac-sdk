package com.xrk.uiac.sdk.test.business;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
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

public class MultiClientTest
{
	private static ExecutorService bgThread1 = Executors.newFixedThreadPool(30);
	private static ExecutorService bgThread2 = Executors.newFixedThreadPool(30);
	private static ExecutorService bgThread3 = Executors.newFixedThreadPool(30);
	private static UiacClient client1 = UiacClient.Create("http://127.0.0.1:8081", "1003", "1.0");
	private static UiacClient client2 = UiacClient.Create("http://127.0.0.1:8082", "1003", "1.0");
	private static UiacClient client3 = UiacClient.Create("http://127.0.0.1:8083", "1003", "1.0");
	
	public static void main(String[] args)
	{
		SingleClient sc1 = new SingleClient(client1, bgThread1);
		SingleClient sc2 = new SingleClient(client2, bgThread2);
		SingleClient sc3 = new SingleClient(client3, bgThread3);
		
		SingleClient[] clients = {sc1, sc2, sc3};
		ExecutorService[] bgThreads = {bgThread1, bgThread2, bgThread3};
		
		intergrationTest(15800000000l, 100, clients, bgThreads, 3);
	}
	
	public static void intergrationTest(long startMobile, int mobileCount, SingleClient[] clients, ExecutorService[] bgThreads, int count)
	{
		for (int i=0; i<mobileCount; i++)
		{
			long mobileLong = startMobile + i;
			String mobile = String.valueOf(startMobile + i);
			
			for (int k=0; k<count; k++)
			{
				final int j = k;
				
				bgThreads[j].execute(new Runnable()
				{
					@Override
					public void run()
					{
						//System.out.println("mobile: " + mobile);
						
						clients[j].createUser.createUser(mobile);
						
						UserAuthorizationEntity uae = clients[j].login.login(mobile);
						if (uae == null)
						{
							return;
						}
						long uid = uae.getUid();
						//String token = uae.getAccessToken();
						//String refreshToken = uae.getRefreshToken();
						
						//clients[j].queryToken.queryToken(refreshToken, uid);
						//clients[j].verifyToken.verifyToken(token);
						//uae = clients[j].updateToken.updateToken(uid, token, refreshToken);
						//token = uae.getAccessToken();
						//clients[j].logout.logout(token);
						//clients[j].loginWithUid.loginWithUid(uid);
						
						clients[j].getUserInfoWithAccount.getUserInfoWithAccount(mobile);
						clients[j].getUserInfoWithUid.getUserInfoWithUid(uid);
						clients[j].updateUserInfo.updateUserInfo(uid);
						
						clients[j].updatePassword.updatePassword(uid);
						clients[j].resetPassword.resetPassword(uid);
						
						clients[j].bindSubAccount.bindSubAccount(uid);
						clients[j].unbindSubAccount.unbindSubAccount(uid);
						clients[j].getSubAccountList.getSubAccountList(uid);
						
						//clients[j].bindMobile.bindMobile(uid, mobileLong);
						clients[j].unbindMobile.unbindMobile(uid);
						clients[j].getBindingStatus.getBindingStatus(uid);
						
//						clients[j].disableUser.disableUser(uid);
//						clients[j].enableUser.enableUser(uid);
//						clients[j].getUserStatus.getUserStatus(uid);
						
						//sendCaptcha.sendCaptcha(mobile);
						//validateCaptcha.validateCaptcha(mobile);
						
						//clients[j].getWenbaTempId.getWenbaTempId();
						clients[j].checkParameter.checkParameter(mobile);
					}
				});
			}
		}
	}
	
	public static class SingleClient
	{
		public UiacClient client = null;
		
		public CreateUserTest createUser = null;
		public GetUserInfoWithAccountTest getUserInfoWithAccount = null;
		public GetUserInfoWithUidTest getUserInfoWithUid = null;
		public UpdateUserInfoTest updateUserInfo = null;
		
		public UpdatePasswordTest updatePassword = null;
		public ResetPasswordTest resetPassword = null;
		
		public BindSubAccountTest bindSubAccount = null;
		public UnbindSubAccountTest unbindSubAccount = null;
		public GetSubAccountListTest getSubAccountList = null;
		
		public BindMobileTest bindMobile = null;
		public UnbindMobileTest unbindMobile = null;
		public GetBindingStatusTest getBindingStatus = null;
		
		public DisableUserTest disableUser = null;
		public EnableUserTest enableUser = null;
		public GetUserStatusTest getUserStatus = null;
		
		public SendCaptchaTest sendCaptcha = null;
		public ValidateCaptchaTest validateCaptcha = null;
		
		public GetWenbaTempIdTest getWenbaTempId = null;
		public CheckParameterTest checkParameter = null;
		
		public LoginTest login = null;
		public LoginWithUidTest loginWithUid = null;
		public LogoutTest logout = null;
		public QueryTokenTest queryToken = null;
		public UpdateTokenTest updateToken = null;
		public VerifyTokenTest verifyToken = null;
		
		public SingleClient(UiacClient client, ExecutorService bgThread)
		{
			this.client = client;
			
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
}

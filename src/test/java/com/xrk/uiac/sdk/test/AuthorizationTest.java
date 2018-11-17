package com.xrk.uiac.sdk.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.entity.UserInfo;
import com.xrk.uiac.sdk.exception.UiacException;

import junit.textui.TestRunner;

public class AuthorizationTest extends TestRunner {
	
	private final static String SERVICE_URL = "http://192.168.6.130:8081";
	private final static String APPID = "2001";
	private final static String VERSION = "1.0.0.1";
	private static UiacClient client;
	
	@Before
	public void setUp() throws Exception {
		client = UiacClient.Create(SERVICE_URL, APPID, VERSION);
	}

	@Test
	public void testLogin() {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName("廖泽雄aaaaaaa");
			UserAuthorizationEntity resp = client.authorization().login("17900000000", "123456");
			UserAuthorizationEntity resp1 = client.authorization().updateToken(resp.getUid(), resp.getAccessToken(),
					resp.getRefreshToken());
			assertEquals(resp.getUid(), resp1.getUid());
			boolean bRtn = client.account().updateUserInfo(10000005, resp1.getAccessToken(), userInfo, null);
			assertTrue(bRtn);
		} catch (UiacException e) {
			System.out.println(String.format("ERROR: code=%s, httpCode=%s, message=%s", e.getErrCode(), e.getHttpCode(),
					e.getMessage()));
		}
	}

	@After
	public void tearDown() throws Exception {
		if (client != null)
			client = null;
	}

}

package com.xrk.uiac.sdk.entity;

import java.util.Date;

/**
 * 
 * UserAuthorizationEntity: UserAuthorizationEntity.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class UserAuthorizationEntity
{
	private long uid;
	private String appId;
	private String accessToken;
	private String refreshToken;
	private long expireDate;
	private long loginDate;
	public long getUid()
    {
	    return uid;
    }
	public void setUid(long uid)
    {
	    this.uid = uid;
    }
	public String getAppId()
    {
	    return appId;
    }
	public void setAppId(String appId)
    {
	    this.appId = appId;
    }
	public String getAccessToken()
    {
	    return accessToken;
    }
	public void setAccessToken(String accessToken)
    {
	    this.accessToken = accessToken;
    }
	public String getRefreshToken()
    {
	    return refreshToken;
    }
	public void setRefreshToken(String refreshToken)
    {
	    this.refreshToken = refreshToken;
    }
	public long getExpireDate()
    {
	    return expireDate;
    }
	public void setExpireDate(long expireDate)
    {
	    this.expireDate = expireDate;
    }
	public long getLoginDate()
    {
	    return loginDate;
    }
	public void setLoginDate(long loginDate)
    {
	    this.loginDate = loginDate;
    }
}

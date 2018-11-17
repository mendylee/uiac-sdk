package com.xrk.uiac.sdk.entity;

/**
 * 
 * CreateUserResponse: CreateUserResponse.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class CreateUserResponse
{
	private long uid;
	private int appId;
	private String userCode;
	
	public long getUid()
	{
		return uid;
	}
	public void setUid(long uid)
	{
		this.uid = uid;
	}
	public int getAppId()
	{
		return appId;
	}
	public void setAppId(int appId)
	{
		this.appId = appId;
	}
	public String getUserCode()
	{
		return userCode;
	}
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}
}

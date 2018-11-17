package com.xrk.uiac.sdk.entity;

import java.util.Date;

/**
 * 
 * PushResponse: PushResponse.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class PushResponse
{
	private String appID;
	private String status;
	private Date regTime;
	private Date delTime;

	public String getAppID()
	{
		return appID;
	}

	public void setAppID(String appID)
	{
		this.appID = appID;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getRegTime()
	{
		return regTime;
	}

	public void setRegTime(Date regTime)
	{
		this.regTime = regTime;
	}

	public Date getDelTime()
	{
		return delTime;
	}

	public void setDelTime(Date delTime)
	{
		this.delTime = delTime;
	}
}

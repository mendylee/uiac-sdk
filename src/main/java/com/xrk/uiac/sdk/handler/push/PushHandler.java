package com.xrk.uiac.sdk.handler.push;

import com.xrk.uiac.sdk.handler.IPushHandler;
import com.xrk.uiac.sdk.notice.INoticeMessage;

/**
 * 消息推送接口实现类
 * PushHandler: PushHandler.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年8月6日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class PushHandler implements IPushHandler
{

	@Override
	public boolean start(int localPort, INoticeMessage callback)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stop()
	{
		// TODO Auto-generated method stub
		return false;
	}

}

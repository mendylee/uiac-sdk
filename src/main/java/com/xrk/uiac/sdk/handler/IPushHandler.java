package com.xrk.uiac.sdk.handler;

import com.xrk.uiac.sdk.notice.INoticeMessage;

/**
 * 推送服务接口
 * IPushHandler: IPushHandler.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年8月6日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public interface IPushHandler
{
	/**
	 * 
	 * 开启消息通知监听服务  
	 *    
	 * @param localPort 本地监听的端口
	 * @param callback 消息回调对象
	 * @return
	 */
	public boolean start(int localPort, INoticeMessage callback);
	
	/**
	 * 
	 * 停止本地监听服务  
	 *    
	 * @return
	 */
	public boolean stop();
}

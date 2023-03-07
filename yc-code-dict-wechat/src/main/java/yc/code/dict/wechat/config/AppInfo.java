package yc.code.dict.wechat.config;

import yc.code.dict.wechat.db.DbType;
import yc.code.dict.wechat.util.StringMethod;

/**
 * 微信开发相关常量
 *
 * <p>1、大部分常量都在这里处理，例如公众号账户信息，微信接口API的URL等
 * <p>2、同时也做一个Storage用,数据库信息,微信信息等
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class AppInfo {
	
	public static String APP_ID;
	public static String APP_SECRET;
	public static String TOKEN;
	public static String WX_ID;
	
	public static DbType DB_TYPE;
	private static String AUTO_TABLE_PREFIX = "t";
	
	/**
	 * 获取access_token的URL
	 */
	public static final String URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	
	/**
	 * 获取网页授权的URL
	 */
	public static final String URL_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	/**
	 * 获取微信用户信息的URL
	 */
	public static final String URL_GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	
	public static void setWeChatMainConfig(WeChatMainConfig weChatMainConfig) {
		APP_ID = weChatMainConfig.getAppId();
		APP_SECRET = weChatMainConfig.getAppSecret();
		TOKEN = weChatMainConfig.getToken();
		WX_ID = weChatMainConfig.getWxId();
	}

	public static String getAutoTablePrefix() {
		return AUTO_TABLE_PREFIX;
	}

	public static void setAutoTablePrefix(String prefix) {
		if (!StringMethod.isEmpty(prefix)) {
			AUTO_TABLE_PREFIX = prefix;
		}
	}
	
}

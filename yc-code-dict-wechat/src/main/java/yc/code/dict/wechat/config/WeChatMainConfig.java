package yc.code.dict.wechat.config;

import lombok.Data;

/**
 * 公众号主要信息
 *
 * <p>这部分信息是必填项
 * <p>优先级最高
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
@Data
public class WeChatMainConfig {
	/** 订阅号、服务号、企业号的AppId */
	private String appId;
	/** 订阅号、服务号、企业号开发者模式的AppSecret */
	private String appSecret;
	/** 接入验证使用的Token */
	private String token;
	/** 订阅号、服务号、企业号的openId */
	private String wxId;
}

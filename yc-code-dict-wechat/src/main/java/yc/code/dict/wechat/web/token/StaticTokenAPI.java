package yc.code.dict.wechat.web.token;

import java.util.Map;

import yc.code.dict.wechat.util.StringMethod;

/**
 * 微信AccessToken的简单实现
 *
 * <p>在不接入Redis或者不接入数据库的情况下，将AccessToken缓存在静态变量中。
 * <p>但是此方法不适合用于集群模式,要注意使用场合,如果申请新的AccessToken,那么老的就都会Not Work。
 * <p>例如：集群模式下，好几台应用服务器，有的正在使用这个AccessToken，突然一台重新申请了，那么其余的就GG了。
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class StaticTokenAPI extends AbstractTokenAPI implements CacheToken{

	private StaticTokenAPI(){}
	
	private static StaticTokenAPI INSTANCE = null;
	
    public static StaticTokenAPI getInstance() {
        if (INSTANCE == null) {
            return new StaticTokenAPI();
        }
        return INSTANCE;
    }
    
    private Long ACCESSTOKEN_GETTIME;
	private String ACCESSTOKEN;

	
	public String getCacheToken() {
		if (ACCESSTOKEN_GETTIME == null || ((System.currentTimeMillis()/1000 - ACCESSTOKEN_GETTIME) >= 7100)) {
			try {
				Map mapTypes = generateAccessToken();
				String accessToken = ((String)mapTypes.get("access_token"));
				if (StringMethod.isEmpty(accessToken)) {
					System.out.println("generateAccessToken error! maybe wechat error? ");
				} else {
					ACCESSTOKEN_GETTIME = System.currentTimeMillis()/1000;
					ACCESSTOKEN = accessToken;
					return ACCESSTOKEN;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("generateAccessToken error! maybe wechat error? ");
				return "error_token";
			}
		}
		return ACCESSTOKEN;
	}
}

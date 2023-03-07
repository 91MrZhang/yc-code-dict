package yc.code.dict.wechat.web.token;

/**
 * 微信AccessToken的数据库实现
 *
 * <p>跟BaseAccessToken差不多
 * <p>加上一个申请时间就行了,有时间我再完善一下这个类 = =！
 * 
 * @author 91MrZhang
 * @since 1.0.0
 */
public class DBTokenAPI extends AbstractTokenAPI implements CacheToken{
	
	private DBTokenAPI(){}
	
	private static DBTokenAPI INSTANCE = null;
	
    public static DBTokenAPI getInstance() {
        if (INSTANCE == null) {
            return new DBTokenAPI();
        }
        return INSTANCE;
    }
	
    
	public String getCacheToken() {
		return null;
	}

}

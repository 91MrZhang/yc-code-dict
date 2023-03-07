package yc.code.dict.wechat.web.token;

/**
 * AccessToken的接口
 * 
 * <p>实际使用的时候，Token缓存方法多种多样，有的在Redis里，有的缓存在数据库里，还有的缓存在静态变量或者序列化存储。
 * <p>这里就留个接口自己实现吧,注意有效时长7200s。
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public interface CacheToken {
	
	String getCacheToken();
	
}

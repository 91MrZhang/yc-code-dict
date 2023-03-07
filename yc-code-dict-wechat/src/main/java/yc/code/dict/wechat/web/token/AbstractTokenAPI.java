package yc.code.dict.wechat.web.token;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import yc.code.dict.wechat.config.AppInfo;
import yc.code.dict.wechat.util.HttpMethod;

/**
 * Token
 *
 * <p>父类共用向腾讯申请Token的方法
 * <p>其余的子类需要去实现接口
 * 
 * @author 91MrZhang
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractTokenAPI implements CacheToken {

	public static Map generateAccessToken() {
		try {
			HashMap<String, String> params = new HashMap<>();
			params.put("grant_type", "client_credential");
			params.put("appid", AppInfo.APP_ID);
			params.put("secret", AppInfo.APP_SECRET);
			String json = HttpMethod.doGet(AppInfo.URL_GET_ACCESS_TOKEN, params);
			return JSON.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("generateAccessToken error! maybe network? ");
			return new HashMap<String, String>();
		}
	}
}

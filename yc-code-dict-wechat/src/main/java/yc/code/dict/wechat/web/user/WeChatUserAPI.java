package yc.code.dict.wechat.web.user;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import yc.code.dict.wechat.config.AppInfo;
import yc.code.dict.wechat.data.WeChatUser;
import yc.code.dict.wechat.util.HttpMethod;
import yc.code.dict.wechat.web.token.AbstractTokenAPI;
import yc.code.dict.wechat.web.token.StaticTokenAPI;

/**
 * WeChatWeb端API.
 *
 * <p>微信网页授权
 * <p>根据openId获取用户信息
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class WeChatUserAPI {

    private static AbstractTokenAPI tokenAPI = StaticTokenAPI.getInstance();

    /**
     * 微信网页授权 如果用户在微信客户端中访问第三方网页，公众号可以通过微信网页授权机制，来获取用户基本信息，进而实现业务逻辑。
     * 以snsapi_base为scope发起的网页授权， 是用来获取进入页面的用户的openid的，
     * 并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
     */
    @SuppressWarnings("rawtypes")
    public static String webpageAuthorization(String code) {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("code", code);
            params.put("appid", AppInfo.APP_ID);
            params.put("secret", AppInfo.APP_SECRET);
            params.put("grant_type", "authorization_code");
            String json = HttpMethod.doGet(AppInfo.URL_OAUTH, params);
            Map map = JSON.parseObject(json);
            return (String) map.get("openid");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("webpageAuthorization fail!");
            return null;
        }

    }

    /**
     * 根据openId获取用户的最新信息
     */
    public static WeChatUser getWeChatUser(String openId) {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("openid", openId);
            params.put("access_token", tokenAPI.getCacheToken());
            String json = HttpMethod.doGet(AppInfo.URL_GET_USERINFO, params);
            return JSON.parseObject(json, WeChatUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getWeChatUser fail!");
            return new WeChatUser();
        }
    }
}

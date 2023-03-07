package yc.code.dict.wechat.data;

import lombok.Data;

@Data
public class WeChatUser {
    private String pk1;
    private String unionid;
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Integer subscribe_time;
    private String remark;
    private String groupid;
    private String tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
}

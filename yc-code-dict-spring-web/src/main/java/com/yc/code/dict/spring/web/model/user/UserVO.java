package com.yc.code.dict.spring.web.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserVO
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Data
@ApiModel("用户VO")
public class UserVO {
    @ApiModelProperty(value = "用户Id", position = 1)
    private String userId;
    @ApiModelProperty(value = "姓名", position = 2)
    private String userName;
    @ApiModelProperty(value = "电话", position = 3)
    private String phone;
    @ApiModelProperty(value = "邮件", position = 4)
    private String email;
    @ApiModelProperty(value = "身份证", position = 5)
    private String idCard;
    @ApiModelProperty(value = "创建时间", position = 6)
    private String createTime;
}

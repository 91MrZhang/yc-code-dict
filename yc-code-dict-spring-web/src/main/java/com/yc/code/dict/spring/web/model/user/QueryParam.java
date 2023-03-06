package com.yc.code.dict.spring.web.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * QueryParam
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Data
@ApiModel("用户查询VO")
public class QueryParam {
    @ApiModelProperty(value = "创建时间-起", position = 1)
    private String createTimeStart;
    @ApiModelProperty(value = "创建时间-止", position = 2)
    private String createTimeEnd;
}

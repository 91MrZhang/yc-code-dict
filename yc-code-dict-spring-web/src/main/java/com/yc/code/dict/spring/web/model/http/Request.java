package com.yc.code.dict.spring.web.model.http;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Request
 *
 * @author zhangyuting
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request<T> {

    /**
     * 下方格式，param包装
     * {
     *   "param": {
     *     "userId": "string",
     *     "userName": "string",
     *     "phone": "string",
     *     "email": "string",
     *     "idCard": "string",
     *     "createTime": "string"
     *   }
     * }
     */
    @Valid
    @NotNull(message = "请求参数不能为空")
    private T param;

    public static <T> Request<T> of(T param) {
        return Request.<T>builder().param(param).build();
    }
}

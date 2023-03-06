package com.yc.code.dict.spring.web.model.http;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * PagedRequest
 *
 * @author zhangyuting
 */
@Getter
@Setter
public class PagedRequest<T> extends Request<T> {

    /**
     * {
     *   "page": {
     *     "pageNum": 0,
     *     "pageSize": 0
     *   },
     *   "param": {
     *     "createTimeStart": "string",
     *     "createTimeEnd": "string"
     *   }
     * }
     *
     */
    @Valid
    @NotNull(message = "分页参数不能为空")
    private PageVO page;

    public static <T> PagedRequest<T> of(T param, PageVO page) {
        PagedRequest<T> req = new PagedRequest<>();
        req.setParam(param);
        req.setPage(page);
        return req;
    }
}

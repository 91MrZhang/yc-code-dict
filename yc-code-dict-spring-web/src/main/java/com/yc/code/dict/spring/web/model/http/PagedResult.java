package com.yc.code.dict.spring.web.model.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * PagedResult
 *
 * @author zhangyuting
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResult<E> {

    private List<E> list;
    private long total;

    public static <T> PagedResult<T> of(List<T> list, long total) {
        return PagedResult.<T>builder().list(list).total(total).build();
    }
}

package com.yc.code.dict.spring.web.model.http;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Response
 *
 * @author zhangyuting
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    public static final int DUPLICATED = -100;
  
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        return Response.<T>builder().code(1).msg("成功").data(data).build();
    }

    public static <E> Response<PagedResult<E>> ok(List<E> list, long total) {
        return ok(new PagedResult<>(list, total));
    }


    public static <T> Response<T> error(int code, String msg) {
        return Response.<T>builder().code(code).msg(msg).build();
    }

    public static <T> Response<T> error(Throwable e) {
        return Response.<T>builder().code(-1).msg(e.getMessage()).build();
    }

}

package com.yc.code.dict.spring.web.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.yc.code.dict.spring.web.model.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RestControllerAdvice
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@RestControllerAdvice
@RestController
public class GlobalHandler {

    /**
     * 捕捉Runtime异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Response<String> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return Response.error(e);
    }

    /**
     * 捕捉全局Exception异常
     * 需要项目自定义Exception，我这里捕获全局了
     * 生产上尽量不要捕获全局
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<String> errorHandler(Exception e) {
        return Response.error(-1, e.getMessage());
    }


    // 请求传来的JSON格式错误
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException e1 = (InvalidFormatException) e.getCause();

            Pattern p = Pattern.compile("\\[\"(\\w+)\"]");
            Matcher matcher = p.matcher(e1.getPathReference());//com.xxx.XXX["code"]
            if (matcher.find()) {
                String field = matcher.group(1);
                message = "[" + field + "]: " + e1.getValue() + "不是合法的" + e1.getTargetType().getSimpleName() + "类型";
            }
        }
        return Response.error(400, message);
    }
}

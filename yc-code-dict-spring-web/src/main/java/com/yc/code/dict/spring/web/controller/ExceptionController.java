package com.yc.code.dict.spring.web.controller;

import com.yc.code.dict.spring.web.model.http.Request;
import com.yc.code.dict.spring.web.model.http.Response;
import com.yc.code.dict.spring.web.model.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExceptionController
 * <p>
 * 这里主要演示一下异常情况下的表现
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Api(tags = "异常测试")
@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor
public class ExceptionController {


    @ApiOperation(value = "testA")
    @PostMapping("/testA")
    public Response<Void> testA() {
        throw new RuntimeException("RuntimeException");
    }


    @ApiOperation(value = "testB")
    @PostMapping("/testB")
    public Response<Void> testB() {
        System.out.println(1 / 0);
        return Response.ok();
    }

    @ApiOperation(value = "testC")
    @PostMapping("/testC")
    public Response<Void> testC() throws Exception {
        throw new Exception("Exception");
    }
}

package com.yc.code.dict.spring.web.controller;

import com.yc.code.dict.spring.web.model.http.PagedRequest;
import com.yc.code.dict.spring.web.model.http.PagedResult;
import com.yc.code.dict.spring.web.model.http.Request;
import com.yc.code.dict.spring.web.model.http.Response;
import com.yc.code.dict.spring.web.model.user.QueryParam;
import com.yc.code.dict.spring.web.model.user.UserVO;
import com.yc.code.dict.spring.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Response<Void> save(@RequestBody Request<UserVO> req) {
        userService.save(req.getParam());
        return Response.ok();
    }

    @ApiOperation(value = "详情")
    @PostMapping("/detail")
    public Response<UserVO> detail(@RequestBody Request<String> req) {
        return Response.ok(userService.detail(req.getParam()));
    }

    @ApiOperation(value = "查询")
    @PostMapping("/list")
    public Response<PagedResult<UserVO>> list(@RequestBody PagedRequest<QueryParam> req) {
        return Response.ok(userService.list(req.getParam(),req.getPage()));
    }
}

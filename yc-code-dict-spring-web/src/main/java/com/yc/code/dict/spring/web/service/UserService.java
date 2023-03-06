package com.yc.code.dict.spring.web.service;

import com.yc.code.dict.spring.web.model.http.PageVO;
import com.yc.code.dict.spring.web.model.http.PagedResult;
import com.yc.code.dict.spring.web.model.user.QueryParam;
import com.yc.code.dict.spring.web.model.user.UserVO;

public interface UserService {

    void save(UserVO param);

    UserVO detail(String userId);

    PagedResult<UserVO> list(QueryParam param, PageVO page);
}

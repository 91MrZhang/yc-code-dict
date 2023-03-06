package com.yc.code.dict.spring.web.service.impl;

import com.yc.code.dict.spring.web.model.http.PageVO;
import com.yc.code.dict.spring.web.model.http.PagedResult;
import com.yc.code.dict.spring.web.model.user.QueryParam;
import com.yc.code.dict.spring.web.model.user.UserVO;
import com.yc.code.dict.spring.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * UserServiceImpl
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public void save(UserVO param) {
        log.info("success");
    }

    @Override
    public UserVO detail(String userId) {
        return new UserVO();
    }

    @Override
    public PagedResult<UserVO> list(QueryParam param, PageVO page) {
        // page方法涉及一个换算问题，不同框架，不同数据库不太相同，在此不多赘述
        // Pageable pageable = PageRequest.of(page.getPageNum() - 1, page.getPageSize());
        return PagedResult.of(Collections.singletonList(new UserVO()), 1);
    }
}

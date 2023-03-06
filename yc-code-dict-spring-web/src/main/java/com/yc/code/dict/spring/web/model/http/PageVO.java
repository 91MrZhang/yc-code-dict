package com.yc.code.dict.spring.web.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PageVO
 *
 * @author zhangyuting
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize;
    private int pageNum;

    public static PageVO of(int num, int size) {
        PageVO pageVO = new PageVO();
        pageVO.setPageSize(size);
        pageVO.setPageNum(num);
        return pageVO;
    }


    public int springPageNum() {//取得从0开始的页号
        return Math.max(getPageNum() - 1, 0);
    }

    public long limit() {
        return (pageSize > 0) ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public long offset() {
        return springPageNum() * limit();
    }

}

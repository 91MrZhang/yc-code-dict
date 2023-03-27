package yc.code.dict.ck;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yc.code.dict.ck.service.SpringJdbcService;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryTester {

    @Autowired
    private SpringJdbcService springJdbcService;

    @Test
    public void testA() {
        List<Map<String, Object>> maps = springJdbcService.queryOriginData("select * from audit_wbapi.dwd_kw_image limit 1");
        System.out.println(maps);
    }
}
package yc.code.dict.ck.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import yc.code.dict.ck.service.SpringJdbcService;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpringJdbcServiceImpl implements SpringJdbcService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> queryOriginData(String querySql) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from audit_wbapi.dwd_kw_image limit 1");
        return maps;
    }
}

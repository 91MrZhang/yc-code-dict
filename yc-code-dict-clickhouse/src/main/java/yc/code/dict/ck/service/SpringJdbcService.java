package yc.code.dict.ck.service;

import java.util.List;
import java.util.Map;

public interface SpringJdbcService {

    List<Map<String, Object>> queryOriginData(String querySql);
}

package yc.code.dict.nacos.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import yc.code.dict.nacos.vo.UserVO;

@FeignClient(name = "yc-code-dict-nacos")
public interface UserFeginClient {

    @GetMapping("/user/getUserInfoServer")
    UserVO getUserInfoServer();
}

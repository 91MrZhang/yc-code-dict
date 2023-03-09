package yc.code.dict.nacos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yc.code.dict.nacos.fegin.UserFeginClient;
import yc.code.dict.nacos.vo.UserVO;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFeginClient userFeginClient;

    @GetMapping("/getUserInfoServer")
    public UserVO getUserInfoServer(){
        UserVO u = new UserVO();
        u.setName("server");
        u.setAge(20);
        return u;
    }

    @GetMapping("/getUserInfoFegin")
    public UserVO getUserInfoFegin(){
        return userFeginClient.getUserInfoServer();
    }
}

package top.help.pal.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import top.help.pal.api.service.UserService;
import top.help.pal.api.token.TokenRequired;
import top.help.pal.common.domain.resp.Result;
import top.help.pal.common.domain.vo.HelloVo;
import top.help.pal.common.entity.UserEntity;
import top.help.pal.common.token.TokenUser;
import top.help.pal.common.utils.TimeUtils;

@RestController
@RequestMapping("/v1")
public class HelloController {

    @Resource
    private UserService userService;

    /**
     * curl -X GET http://localhost:8888/v1/ping
     *
     * @return
     */
    @GetMapping({"", "/", "/ping", "test"})
    public Result<HelloVo> ping() {
        return Result.success(HelloVo.builder().hello("pong").build());
    }

    /**
     * curl -X POST http://localhost:8888/v1/user
     *
     * @return
     */
//    @PostMapping("/user")
    public Result<UserEntity> testSaveUser() {
        UserEntity save = UserEntity.builder().deviceId(TimeUtils.getCurrentTimeMils() + "").username("test").build();
        userService.save(save);
        return Result.success(save);
    }


    /**
     * curl -X GET http://localhost:8888/v1/auth -H 'Authorization: xxx' -H 'did: 123432'
     *
     * @return
     */
    @GetMapping({"/auth"})
    public Result<HelloVo> auth(@TokenRequired TokenUser user) {
        return Result.success(HelloVo.builder().hello(user.getUid().toString()).build());
    }

}

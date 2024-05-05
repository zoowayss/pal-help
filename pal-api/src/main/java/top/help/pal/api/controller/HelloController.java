package top.help.pal.api.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.help.pal.api.service.UserService;
import top.help.pal.common.domain.resp.Result;
import top.help.pal.common.domain.vo.HelloVo;
import top.help.pal.common.entity.UserEntity;
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

}

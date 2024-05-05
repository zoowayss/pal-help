package top.help.pal.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.help.pal.api.mapper.UserMapper;
import top.help.pal.api.service.UserService;
import top.help.pal.common.entity.UserEntity;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}

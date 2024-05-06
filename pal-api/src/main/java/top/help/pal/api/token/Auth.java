package top.help.pal.api.token;

import top.help.pal.common.domain.enums.SysRoleEnum;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    SysRoleEnum[] requires() default {SysRoleEnum.user};
}

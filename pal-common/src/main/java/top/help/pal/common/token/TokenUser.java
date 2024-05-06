package top.help.pal.common.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.ognl.OgnlRuntime;
import top.help.pal.common.domain.enums.SysRoleEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenUser {

    private Long uid;

    private String did;

    private SysRoleEnum role;

}
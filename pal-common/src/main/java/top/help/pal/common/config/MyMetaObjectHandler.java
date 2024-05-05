package top.help.pal.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.help.pal.common.utils.TimeUtils;

import java.util.Date;

//交给spring管理
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    //使用mp实现添加操作,这个方法会执行,metaObject元数据(表中的名字,表中的字段)
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        Long current = TimeUtils.getCurrentTimeMils();
        this.setFieldValByName("createTime", current, metaObject);
        this.setFieldValByName("updateTime", current, metaObject);
    }

    //使用mp实现修改操作,这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", TimeUtils.getCurrentTimeMils(), metaObject);
    }

}
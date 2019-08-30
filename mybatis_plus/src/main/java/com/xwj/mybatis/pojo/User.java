package com.xwj.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")//默认驼峰命名
public class User {
    @TableId(type = IdType.UUID)
    private String id;
    @TableField(value = "name")
    private String userName;
    private Integer age;
}

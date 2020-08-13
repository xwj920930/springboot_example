package com.xwj.validator.model;

import com.xwj.validator.group.ServiceGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {
    @NotNull(message = "id不能为空", groups = {ServiceGroup.ServiceOperate.class})
    @Min(value = 1,message = "id不能小于为1",groups = {ServiceGroup.ServiceOperate.class})
    private int id;
    @NotBlank(message = "用户名不能为空", groups = {ServiceGroup.class})
    @Length(min = 1,max = 10,message = "用户名长度必须在1-10之间",groups = {ServiceGroup.class})
    private String name;
}

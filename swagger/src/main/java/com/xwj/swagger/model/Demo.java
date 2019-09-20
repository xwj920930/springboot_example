package com.xwj.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//ApiModel主要用于swagger页面展示
@ApiModel
public class Demo implements Serializable {
//    高版本swagger中实体类中，Integer类型的属性加@ApiModelProperty时，
//    必须要给example参数赋值，且值必须为数字类型
    @ApiModelProperty(value = "订单ID",example = "123")
    private Integer id;
    @ApiModelProperty(value = "订单名字",example = "item")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

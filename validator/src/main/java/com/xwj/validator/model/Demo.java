package com.xwj.validator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @NotEmpty 用在集合类上面，值不为null且不为空
 * @NotBlank 用在String上面，不为空字符串
 * @NotNull 用在基本类型上，不为null
 */
@Getter
@Setter
@ToString
public class Demo implements Serializable {
    @NotNull(message = "id不能为空")
    @Min(value = 0,message = "id不能小于为零")
    private int id;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1,max = 10,message = "用户名长度必须在1-10之间")
    private String name;
    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "1000",message = "价格不能小于1000")
    private BigDecimal price;
    @Email(message = "邮件格式错误")
    private String email;
}

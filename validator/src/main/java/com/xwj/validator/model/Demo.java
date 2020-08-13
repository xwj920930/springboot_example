package com.xwj.validator.model;

import com.xwj.validator.group.ControllerGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
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
    @Valid
    private User user;
    @NotNull(message = "价格不能为空",groups = {ControllerGroup.class})
    @DecimalMin(value = "1000",message = "价格不能小于1000", groups = {ControllerGroup.class})
    private BigDecimal price;
    @Email(message = "邮件格式错误")
    private String email;
}

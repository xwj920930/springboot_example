@NotEmpty 用在集合类上面，值不为null且不为空
@NotBlank 用在String上面，不为空字符串
@NotNull  用在基本类型上，不为null

@size (min=3, max=20, message="用户名长度只能在3-20之间")

@Length(min = 5, max = 20, message = "用户名长度必须位于5到20之间")  

@Email(message = "比如输入正确的邮箱")  

@NotNull(message = "用户名称不能为空") 

@Max(value = 100, message = "年龄不能大于100岁") 

@Min(value= 18 ,message= "必须年满18岁！" )  

@AssertTrue(message = "bln4 must is true")
 
@AssertFalse(message = "blnf must is falase")

@DecimalMax(value="100",message="decim最大值是100")

@DecimalMin(value="100",message="decim最小值是100")

@NotNull(message = "身份证不能为空") 

@Pattern(regexp="^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message="身份证格式错误")

@Valid 支持嵌套校验

@Validated 支持分组(controller层和service层)
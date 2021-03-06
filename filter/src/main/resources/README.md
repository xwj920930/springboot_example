**过滤器**
过滤器的英文名称为 Filter, 是 Servlet 技术中最实用的技术。如同它的名字一样，过滤器是处于客户端和服务器资源文件之间的一道过滤网，帮助我们*过滤掉一些
不符合要求的请求*，通常用作 Session 校验，判断用户权限，如果不符合设定条件，则会被拦截到特殊的地址或者基于特殊的响应。

**拦截器**
Java中的拦截器是动态拦截 action 调用的对象，然后提供了可以在 action 执行前后*增加一些操作*，也可以在 action 执行前停止操作，功能与过滤器类似，
但是标准和实现方式不同。

登录认证：在一些应用中，可能会通过拦截器来验证用户的登录状态，如果没有登录或者登录失败，就会给用户一个友好的提示或者返回登录页面，当然大型项目中都不采
用这种方式，都是调单点登录系统接口来验证用户。记录系统日志：我们在常见应用中，通常要记录用户的请求信息，比如请求 ip，方法执行时间等，通过这些记录可以
监控系统的状况，以便于对系统进行信息监控、信息统计、计算 PV、性能调优等。通用处理：在应用程序中可能存在所有方法都要返回的信息，这是可以利用拦截器来实
现，省去每个方法冗余重复的代码实现。

**监听器**
监听器通常用于监听 Web 应用程序中对象的*创建、销毁等动作*的发送，同时对监听的情况作出相应的处理，最常用于统计网站的在线人数、访问量等。

监听器大概分为以下几种：
ServletContextListener：用来监听 ServletContext 属性的操作，比如新增、修改、删除。
HttpSessionListener：用来监听 Web 应用种的 Session 对象，通常用于统计在线情况。
ServletRequestListener：用来监听 Request 对象的属性操作。

**ApplicationEventPublisher**
可作为观察者模式的一个实现方式，用于业务代码的监听。

其他常用spring方法可以参见SpringWebTest工程
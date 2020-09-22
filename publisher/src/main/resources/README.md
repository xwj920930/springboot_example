一：观察者模式（Observer），又叫发布-订阅模式（Publish/Subscribe），定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都
会得到通知并自动更新

二：实现方法
1.自定义需要发布的事件类(或者忽略该步骤)，需要继承ApplicationEvent类或PayloadApplicationEvent<T>(该类也仅仅是对ApplicationEvent的一层封装)
2.使用@EventListener来监听事件
3.使用ApplicationEventPublisher来发布自定义事件（@Autowired注入即可）

三：注意事项
1、监听器方法中一定要try-catch异常，否则会造成发布事件(有事务的)的方法进行回滚
2、可以使用@Order注解来控制多个监听器的执行顺序，@Order传入的值越小，执行顺序越高
3、对于需要进行s事务监听或不想try-catch runtime异常，可以使用@TransactionalEventListener注解

四：@TransactionalEventListener
如果事件的发布不是在事务（@Transactional）范围内，则监听不到该事件，除非将fallbackExecution标志设置为true
（@TransactionalEventListener(fallbackExecution = true)）;如果在事务中，可以选择在事务的哪个阶段来监听事件，默认在事务提交后监听

五：@Async
如果需要异步则需要开启异步支持，在监听器方法加上注解即可
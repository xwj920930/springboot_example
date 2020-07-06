package com.xwj.mockmvc.controller;

import com.xwj.mockmvc.model.A;
import com.xwj.mockmvc.model.B;
import com.xwj.mockmvc.model.User;
import com.xwj.mockmvc.service.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/*
 * 常用方法：
 * Mockito.mock(classToMock)	模拟对象
 * Mockito.anyString()	模拟入参
 * Mockito.mock(classToMock,defaultAnswer)	使用默认Answer模拟对象
 * Mockito.verify(mock)	验证行为是否发生
 * Mockito.when(methodCall)	模拟调用对象
 * Mockito.when(methodCall).thenReturn(value1).thenReturn(value2)	触发时第一次返回value1，第n次都返回value2
 * Mockito.when(methodCall).thenAnswer(answer))	预期回调接口生成期望值
 * Mockito.doThrow(toBeThrown).when(mock).[method]	模拟抛出异常。
 * Mockito.doReturn(toBeReturned).when(mock).[method]	参数匹配（直接执行不判断）
 * Mockito.doAnswer(answer).when(methodCall).[method]	预期回调接口生成期望值（直接执行不判断）
 * Mockito.doNothing().when(mock).[method]	不做任何返回
 * Mockito.doCallRealMethod().when(mock).[method] //等价于Mockito.when(mock.[method]).thenCallRealMethod();	调用真实的方法
 * Mockito.spy(Object)	用spy监控真实对象,设置真实对象行为
 * reset(mock)	重置mock
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoTest {
    @Spy
    private UserService userService;

    @Mock
    private UserService userService1;
    /*
    @Spy修饰的外部类，必须是真实存在的
    @Mock修饰的外部类,是完全模拟出来的，就算项目中没有这个类的实例，也能自己mock出来一个

    when().thenReturn() 会先执行一遍when里面的语句
    doReturn().when()   直接执行return
    * */

    //Mockito.verify(mock)	验证行为是否发生
    @Test
    public void verify(){
        //模拟对象
        UserService mock = Mockito.mock(UserService.class);
        mock.getUser();
        //验证方法是否执行
        Mockito.verify(mock).getUser();
    }

    //Mockito.when(methodCall).thenReturn(value1, value2)	触发时第一次返回value1，第n次都返回value2
    @Test
    public void when(){
        UserService mock = Mockito.mock(UserService.class);
        Mockito.when(mock.getUser()).thenReturn(new User(1, "123"), new User(2, "234"));
        TestCase.assertEquals("123", mock.getUser().getName());
        TestCase.assertEquals("234", mock.getUser().getName());
    }

    //验证异常
    @Test(expected = NullPointerException.class)
    public void exception(){
        UserService mock = Mockito.mock(UserService.class);
        Mockito.when(mock.exception()).thenThrow(new NullPointerException());
        mock.exception();
    }

    //使用RETURNS_DEEP_STUBS模拟普通对象的嵌套调用
    @Test
    public void deepStubsTest(){
        A a=Mockito.mock(A.class,Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(a.getB().getName()).thenReturn("Beijing");
        TestCase.assertEquals("Beijing",a.getB().getName());
        //等价于下面
//        A a=Mockito.mock(A.class);
//        B b=Mockito.mock(B.class);
//        Mockito.when(a.getB()).thenReturn(b);
//        Mockito.when(b.getName()).thenReturn("Beijing");
//        TestCase.assertEquals("Beijing",a.getB().getName());
    }

    //调用真实方法
    @Test
    public void real(){
        UserService mock = Mockito.mock(UserService.class);
        Mockito.doCallRealMethod().when(mock).getName();
        String name = userService.getName();
        TestCase.assertEquals("xwj", name);
    }

    @Test
    public void any(){
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn(1);
        TestCase.assertEquals(1, list.get(0));
        TestCase.assertEquals(1, list.get(99));
    }

    //不做任何返回
    @Test
    public void doNothing() {
        B b = Mockito.mock(B.class);
        //void 方法才能调用doNothing()
        Mockito.doNothing().when(b).setName(Mockito.anyString());
        b.setName("bb");
        TestCase.assertNull(b.getName());
    }

    //自定义参数匹配
    @Test
    public void argumentMatchersTest() {
        //创建mock对象
        List mock = Mockito.mock(List.class);
        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        Mockito.when(mock.addAll(Mockito.argThat(new IsListOfTwoElements()))).thenReturn(true);
        TestCase.assertTrue(mock.addAll(Arrays.asList("one", "two", "three")));
    }

    static class IsListOfTwoElements implements ArgumentMatcher<List> {
        @Override
        public boolean matches(List list) {
            return list.size() == 3;
        }
    }

    //预期回调接口生成期望值
    @Test
    public void answer_with_callback() {
        List mockList = Mockito.mock(List.class);
        //使用Answer来生成我们我们期望的返回
        Mockito.when(mockList.get(Mockito.anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        });
        TestCase.assertEquals("hello world:0", mockList.get(0));
        TestCase.assertEquals("hello world:999", mockList.get(999));
        //下面写法类似，但是有问题
//        Mockito.doAnswer(invocation -> {
//            Object[] args = invocation.getArguments();
//            return "hello world:" + args[0];
//        }).when(mockList.get(Mockito.anyInt()));
//        TestCase.assertEquals("hello world:0", mockList.get(0));
//        TestCase.assertEquals("hello world:999", mockList.get(999));
    }

    //上面的简洁写法
    @Test
    public void A(){
        //mock对象使用Answer来对未预设的调用返回默认期望值
        List mock = Mockito.mock(List.class, invocation -> 999);
         //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
         TestCase.assertEquals(999, mock.get(1));
         //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
         TestCase.assertEquals(999,mock.size());
    }

    //匹配任意
    @Test(expected = IndexOutOfBoundsException.class)
    public void spy_on_real_objects(){
        List<Integer> list = new LinkedList<>();
        List<Integer> spy = Mockito.spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        Mockito.when(spy.get(0)).thenReturn(3);
        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        Mockito.doReturn(999).when(spy).get(999);
        //预设size()期望值
        Mockito.when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        TestCase.assertEquals(100, spy.size());
        TestCase.assertEquals(1, spy.get(0).intValue());
        TestCase.assertEquals(2, spy.get(1).intValue());
        TestCase.assertEquals(999, spy.get(999).intValue());
    }

    //除了以下情况，先when和先doReturn没有区别
    //Mockito.spy+when(...) thenReturn(...)会调用真实的方法
    @Test(expected = IndexOutOfBoundsException.class)
    public void spy1(){
        List<Integer> list = new LinkedList<>();
        List<Integer> spy = Mockito.spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        Mockito.when(spy.get(0)).thenReturn(3);
    }

    //Mockito.spy+doReturn(...) when(...) 不会调用真实方法
    @Test
    public void spy2(){
        List<Integer> list = new LinkedList<>();
        List<Integer> spy = Mockito.spy(list);
        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        Mockito.doReturn(999).when(spy).get(999);
        //预设size()期望值
        Mockito.when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        TestCase.assertEquals(100, spy.size());
        TestCase.assertEquals(1, spy.get(0).intValue());
        TestCase.assertEquals(2, spy.get(1).intValue());
        TestCase.assertEquals(999, spy.get(999).intValue());
    }

    //Mockito.doNothing().when(mock).[method]	返回值为void的方法调用
    @Test
    public void mockVoid(){
        Mockito.doNothing().when(userService).setName();
    }

}
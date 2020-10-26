package com.zuoxiao.app.proxy;

import com.zuoxiao.app.interf.PrintInterface;
import com.zuoxiao.app.pojo.Person;
import com.zuoxiao.app.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/26 9:03
 */
@SpringBootTest
public class ProxyTest {

    /**
     * 动态代理原理
     * 1.jvm在运行时动态的生成一个Proxy&0的类，继承Proxy，实现被代理的接口targetClass实现的接口targetInterface
     * 2.生成的Proxy&0类只有一个实例，那就是InvocationHandler对象的实例；
     * 3.因为Proxy&0类实现了接口targetInterface，所以可以调用和targetClass一样的方法；
     * 4.Proxy&0类通过反射技术和targetInterface的方法信息，获取到各个方法的Method对象；
     * 5.在调用Proxy&0类的各个方法时，Proxy&0类间接调用了InvocationHandler实例的invoke方法；
     * 6.而InvocationHandler实例的invoke方法再次利用了反射技术，通过传入参数：targetClass对象实例、Method对象实例、参数列表，实现了方法的调用；
     *
     * 为什么动态代理没法去代理对象类，只能代理接口？
     * 网上有很多说法，有几点比较有说服力: 1.代理接口会更灵活，可以使得这个代理对象去代理更多的实现类；
     * 也有很多说是因为java是单继承，而动态代理类已经继承了Proxy类，所以无法再继承别的类。我觉得有些牵强，作者完全可以用别的方式来避免继承Proxy类，
     * 应该是作者设计时认为代理接口能够有更灵活的使用方式，才这么做的。
     * */
    @Test
    public void test() {
        User user1 = new User();
        user1.setAge(2);
        user1.setName("zzz");
        PrintInterface user =
                (PrintInterface) Proxy.newProxyInstance(PrintInterface.class.getClassLoader(), new Class[]{PrintInterface.class}, new UserProxyHandler(user1));
        user.print();
    }

    @Test
    public void test2() {
        //测试动态代理是否可以代理类对象而不是接口
        //结果：不可以代理类对象，只能代理接口
        User user1 = new User();
        user1.setId(22222L);
        user1.setAge(2);
        user1.setName("zzz");
        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},new PersonProxyHandler(user1));
        System.out.println(person.getId());
    }
}

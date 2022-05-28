package com.meituan.xhyzjiji.number.framework.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static class DynamicProxy implements InvocationHandler {
        private Object proxyTarget;

        public Object createProxyClass(Object targetInstance) throws Exception {
            this.proxyTarget = targetInstance;
            Class<?> clazz = proxyTarget.getClass();
            // 生成Proxy类就在java.lang.reflect.Proxy.ProxyClassFactory.apply这里了，最终调用native方法的defineClass0，会把一些代理
            // 信息缓存起来，但是缓存不得超过65536个
            return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        }

        @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxy class name: " + proxy.getClass().getName());
            Object result = method.invoke(proxyTarget, args);
            System.out.println("proxy class method invocation done");
            return result;
        }
    }

    public interface DoService {
        void doSth(String arg);
    }

    public static class GenericClass implements DoService {
        @Override public void doSth(String arg) {
            System.out.println(this.getClass().getName() + " handle message: " + arg);
        }
    }

    public static void main(String[] args) throws Exception {
        GenericClass genericInstance = new GenericClass();
        DynamicProxy dynamicGenerator = new DynamicProxy();
        DoService dynamicInstance = (DoService)dynamicGenerator.createProxyClass(genericInstance);
        dynamicInstance.doSth("Hello world!");
    }

}

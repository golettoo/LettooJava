package cn.lettoo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {

    public static IService getServiceProxy(final IService service) {
        InvocationHandler handler = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                beforePrint();
                Object result = method.invoke(service, args);
                afterPrint();
                return result;
            }
        };

        return (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[] { IService.class },
                                                 handler);
    }
    
    private static void beforePrint() {
        System.out.println("Before print.");
    }

    private static void afterPrint() {
        System.out.println("Before print.");
    }
    
}

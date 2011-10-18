package cn.lettoo.proxy;

public class Client {

    public static void main(String[] args) {
        IService service = new AnotherService();        
        IService proxy = ServiceFactory.getServiceProxy(service);        
        proxy.execute();
    }

}

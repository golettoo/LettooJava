package cn.lettoo.proxy;

public class PrintServiceProxy implements IService {

    IService printSerivce;

    public PrintServiceProxy(IService service) {
        this.printSerivce = service;
    }

    public void setPrintSerivce(IService printSerivce) {
        this.printSerivce = printSerivce;
    }

    public void execute() {
        this.beforePrint();
        this.printSerivce.execute();
        this.afterPrint();
    }

    private void beforePrint() {
        System.out.println("Before print.");
    }

    private void afterPrint() {
        System.out.println("Before print.");
    }

}

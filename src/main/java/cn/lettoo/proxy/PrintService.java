package cn.lettoo.proxy;

public class PrintService implements IService {

    public void execute() {
        System.out.println("The Print Service works.");
    }
}

package cn.lettoo.thread.waitnotify;

public class Producer extends Base{

    public Producer(String name, Store store) {
        super(name, store);
    }

    public void run() {
        int i = 1;
        while(true) {
            Product product = new Product();
            product.setId(i);
            product.setProducer(this);
            store.put(product);
            i++;
            super.sleep();
        }
    }
    
    
}

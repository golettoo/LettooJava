package cn.lettoo.thread.waitnotify;

public class Consumer extends Base {

    public Consumer(String name, Store store) {
        super(name, store);
    }
    
    public void run() {
        while(true) {
            Product product = store.get(this);
            System.out.println(this.name + " consume the product " + product.getId());
            super.sleep();
        }
    }

}

package cn.lettoo.thread;

public class ProducerConsumer {

    public static void main(String[] args) {
        ProductStack ps = new ProductStack();

        Productor p = new Productor(ps, "生产者1");

        Consumer c = new Consumer(ps, "消费者1");

        new Thread(p).start();

        new Thread(c).start();
    }
}

class Product {
    private int id;

    private Productor productor;

    private Consumer consumer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}

class Productor implements Runnable {

    private String name;
    private ProductStack ps = null;

    public Productor(ProductStack ps, String name) {
        this.ps = ps;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setId(i);
            product.setProductor(this);
            ps.push(product);
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return this.name;
    }
}

class Consumer implements Runnable {
    private String name;

    private ProductStack ps = null;

    Consumer(ProductStack ps, String name) {
        this.ps = ps;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            Product product = ps.pop(this);
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return this.name;
    }
}

class ProductStack {
    int index = 0;

    Product[] arrProduct = new Product[6];

    // push使用来让生产者放置产品的
    public synchronized void push(Product product) {
        // 如果仓库满了
        if (index == arrProduct.length) {
            try {
                System.out.println(product.getProductor().getName()
                        + " is waiting.");
                // 等待，并且从这里退出push()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(product.getProductor().getName() + " sent a notifyAll().");

        // 因为我们不确定有没有线程在wait()，所以我们既然生产了产品，就唤醒有可能等待的消费者，让他们醒来，准备消费
        notifyAll();
        // 注意，notifyAll()以后，并没有退出，而是继续执行直到完成。
        arrProduct[index] = product;
        index++;
        System.out.println(product.getProductor().getName() + " 生产了: " + product);
    }

    // pop用来让消费者取出产品的
    public synchronized Product pop(Consumer consumer) {
        // 如果仓库空了
        if (index == 0) {
            try {
                // here will be the consumer thread instance will be waiting ,
                // because empty
                System.out.println(consumer.getName() + " is waiting.");
                // 等待，并且从这里退出pop()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(consumer.getName() + " sent a notifyAll().");
        // 因为我们不确定有没有线程在wait()，所以我们既然消费了产品，就唤醒有可能等待的生产者，让他们醒来，准备生产
        notifyAll();
        // 注意，notifyAll()以后，并没有退出，而是继续执行直到完成。
        // 取出产品
        index--;
        Product product = arrProduct[index];
        product.setConsumer(consumer);
        System.out.println(product.getConsumer().getName() + " 消费了: " + product);
        return product;
    }
}
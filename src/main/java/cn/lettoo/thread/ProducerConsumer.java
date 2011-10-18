package cn.lettoo.thread;

public class ProducerConsumer {

    public static void main(String[] args) {
        ProductStack ps = new ProductStack();

        Productor p = new Productor(ps, "������1");

        Consumer c = new Consumer(ps, "������1");

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

    // pushʹ�����������߷��ò�Ʒ��
    public synchronized void push(Product product) {
        // ����ֿ�����
        if (index == arrProduct.length) {
            try {
                System.out.println(product.getProductor().getName()
                        + " is waiting.");
                // �ȴ������Ҵ������˳�push()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(product.getProductor().getName() + " sent a notifyAll().");

        // ��Ϊ���ǲ�ȷ����û���߳���wait()���������Ǽ�Ȼ�����˲�Ʒ���ͻ����п��ܵȴ��������ߣ�������������׼������
        notifyAll();
        // ע�⣬notifyAll()�Ժ󣬲�û���˳������Ǽ���ִ��ֱ����ɡ�
        arrProduct[index] = product;
        index++;
        System.out.println(product.getProductor().getName() + " ������: " + product);
    }

    // pop������������ȡ����Ʒ��
    public synchronized Product pop(Consumer consumer) {
        // ����ֿ����
        if (index == 0) {
            try {
                // here will be the consumer thread instance will be waiting ,
                // because empty
                System.out.println(consumer.getName() + " is waiting.");
                // �ȴ������Ҵ������˳�pop()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(consumer.getName() + " sent a notifyAll().");
        // ��Ϊ���ǲ�ȷ����û���߳���wait()���������Ǽ�Ȼ�����˲�Ʒ���ͻ����п��ܵȴ��������ߣ�������������׼������
        notifyAll();
        // ע�⣬notifyAll()�Ժ󣬲�û���˳������Ǽ���ִ��ֱ����ɡ�
        // ȡ����Ʒ
        index--;
        Product product = arrProduct[index];
        product.setConsumer(consumer);
        System.out.println(product.getConsumer().getName() + " ������: " + product);
        return product;
    }
}
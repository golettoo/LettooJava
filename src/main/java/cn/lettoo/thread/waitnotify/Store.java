package cn.lettoo.thread.waitnotify;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static final int CAPACITY = 10;
    
    private List<Product> products = new ArrayList<Product>(CAPACITY);

    public synchronized Product get(Consumer consumer) {
        while (this.products.size() == 0) {
            try {
                System.out.println("The store is empty and waiting...");
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        
        Product product = this.products.remove(0);
        product.setConsumer(consumer);
        
        notifyAll();
        
        return product;
    }

    public synchronized void put(Product product) {
        // if the store is full
        while (this.products.size() == CAPACITY) {
            try {
                System.out.println("The store is full and waiting...");
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        
        this.products.add(product);
        System.out.println("The " + product.getProducer().getName() + " produce the product " + product.getId());
        
        notifyAll();        
    }
    
    public static void main(String[] args) {
        Store store = new Store();
        new Thread(new Producer("Producer 1", store)).start();
        new Thread(new Consumer("Consumer 1", store)).start();
    }

}

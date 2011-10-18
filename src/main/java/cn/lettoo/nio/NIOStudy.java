package cn.lettoo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NIOStudy {

    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream("test.txt");
            FileChannel fc = fin.getChannel();
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            fc.read(buffer);
            
            System.out.println(buffer.capacity());
            System.out.println(buffer.limit());
            System.out.println(buffer.position());
            
            buffer.flip();
            System.out.println(buffer.capacity());
            System.out.println(buffer.limit());
            System.out.println(buffer.position());
            
            System.out.println(buffer.get());
            System.out.println(buffer.get());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

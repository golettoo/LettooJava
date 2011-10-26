package cn.lettoo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {

    public static void main(String[] args) throws Exception {
        String inFile = "test.txt";
        String outFile = "test1.txt";
        
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);
        
        FileChannel fcin = fis.getChannel();
        FileChannel fcout = fos.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(50);
        
        while (true) {
            buffer.clear();
            
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            
            buffer.flip();
            
            fcout.write(buffer);
        }
    }
}

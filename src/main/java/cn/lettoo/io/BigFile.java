package cn.lettoo.io;

import java.util.*;
import java.io.*;

public class BigFile implements Iterable<String> {

    private BufferedReader reader;

    public BigFile(String filePath) throws Exception {
        reader = new BufferedReader(new FileReader(filePath));
    }

    public void Close() {
        try {
            reader.close();
        } catch (Exception ex) {
        }
    }

    public Iterator<String> iterator() {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<String> {

        private String currentLine;

        public boolean hasNext() {
            try {
                currentLine = reader.readLine();
            } catch (Exception ex) {
                currentLine = null;
                ex.printStackTrace();
            }

            return currentLine != null;
        }

        public String next() {
            return currentLine;
        }

        public void remove() {
        }
    }
}

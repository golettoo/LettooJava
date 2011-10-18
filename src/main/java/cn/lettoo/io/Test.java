package cn.lettoo.io;


public class Test {

    public static void main(String[] args) throws Exception {
        BigFile file = new BigFile("C:\\bigfile.txt");
        
        for (String line : file)
            System.out.println(line);
        
        file.Close();
    }
}

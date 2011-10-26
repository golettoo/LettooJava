package cn.lettoo.hash;

import java.util.HashMap;
import java.util.Map;

public class HashTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 3;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100; i ++) {
            String key = "key" + i;
            int hashMod = Math.abs(key.hashCode() % n);
            //System.out.println(hashMod);
            if (!map.containsKey(hashMod)) {
                map.put(hashMod, 0);
            }
            int count = map.get(hashMod);
            count ++;
            map.put(hashMod, count);
        }
        
        System.out.println(map);
        
        n = 4;
        for (int i = 100; i<200; i++) {
            String key = "key" + i;
            int hashMod = Math.abs(key.hashCode() % n);
            //System.out.println(hashMod);
            if (!map.containsKey(hashMod)) {
                map.put(hashMod, 0);
            }
            int count = map.get(hashMod);
            count ++;
            map.put(hashMod, count);
        }
        
        System.out.println(map);
        
        System.out.println(Math.pow(2, 32));
    }

}

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBreakPoint {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 2000; i++) {
            // list.add(i);
        }

        TestBreakPoint point = new TestBreakPoint();
        List<List<Integer>> newList = point.spliteList(list, 2000);
        if (newList != null && !newList.isEmpty()) {
            for (List<Integer> a : newList) {
                System.out.println(a);
            }
        }

    }

    protected List spliteList(List list, int size) {
        if (null == list || list.size() == 0 || size == 0) {
            return null;
        }
        int arraySize = 0;
        if (list.size() % size == 0) {
            arraySize = list.size() / size;
        } else {
            arraySize = list.size() / size + 1;
        }
        List result = new ArrayList(arraySize);
        int temp = 0;
        while (temp < arraySize) {
            try {
                if (temp == arraySize - 1) {
                    result.add(list.subList(temp * size, list.size()));
                } else {
                    result.add(list.subList(temp * size, temp * size + size));
                }
            } catch (RuntimeException e) {

            }
            temp++;
        }
        return result;
    }
}

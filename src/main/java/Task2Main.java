import java.util.ArrayList;

public class Task2Main {
    public static void main(String[] args) {
        ArrayListMaker<Integer> arrayListMaker = new ArrayListMaker<>();
        ArrayList<Integer> arrayList = arrayListMaker.arrayToArrayList(new Integer[]{1,2,3});
        System.out.println(arrayList);
    }
}

class ArrayListMaker<T> {
    ArrayList<T> arrayToArrayList(T[] arr) {
        ArrayList<T> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}

import java.util.Arrays;
import java.util.HashSet;

public class IntArrayMethods {

    // task 2
    public static int[] extractAllAfterFour(int[] arr) {
        return extractAllAfterNumber(arr, 4);
    }

    public static int[] extractAllAfterNumber(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == number)
                return Arrays.copyOfRange(arr, i + 1, arr.length);
        }
        throw new RuntimeException();
    }

    // task 3
    public static boolean checkIfOnlyOnesAndFoursInArray(int[] arr) {
        return checkIfOnlySuchNumbersInArray(arr, 1, 4);
    }

    public static boolean checkIfOnlySuchNumbersInArray(int[] arr, int... numbers) {
        HashSet<Integer> numbersSet = new HashSet<>();
        for (int n : numbers)
            numbersSet.add(n);

        for(int i : arr)
            if(!numbersSet.contains(i))
                return false;

        return true;
    }
}

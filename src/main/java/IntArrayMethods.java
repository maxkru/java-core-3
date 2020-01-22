import java.util.Arrays;

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

}

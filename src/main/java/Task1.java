import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        Integer[] arrInteger = new Integer[]{1,2,3};
        Cat[] arrCat = new Cat[]{ new Cat("Барсик"), new Cat("Персик"), new Cat("Васька") };
        swapElements(arrInteger, 0, 1);
        swapElements(arrCat, 0, 1);
        System.out.println(Arrays.toString(arrInteger));
        System.out.println(Arrays.toString(arrCat));
    }

    public static void swapElements(Object[] array, int i, int j) {
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}

class Cat {
    private String name;

    Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
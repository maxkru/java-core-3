package Task3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> arrayList;

    public Box() {
        arrayList = new ArrayList<>();
    }

    public float getWeight() {
        float result = 0f;
        for(T fruit : arrayList)
            result += fruit.getWeight();
        return result;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void pourInto(Box<T> box) {
        box.arrayList.addAll(this.arrayList);
        this.arrayList.clear();
    }

    public void add(T fruit) {
        arrayList.add(fruit);
    }
}

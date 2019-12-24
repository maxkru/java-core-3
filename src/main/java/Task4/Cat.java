package Task4;

import java.io.Serializable;

public class Cat implements Serializable {
    String name;
    int age;

    Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Кот " + name + ", " + age + " лет";
    }
}

package Task3;


public class Task3Main {
    public static void main(String[] args) {
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 2; i++) {
            orangeBox.add(new Orange());
        }
        System.out.println("orangeBox.getWeight() = " + orangeBox.getWeight());

        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 2; i++) {
            appleBox.add(new Apple());
        }
        System.out.println("appleBox.getWeight() = " + appleBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(new Apple());
        appleBox.pourInto(appleBox2);
        System.out.println(appleBox2.compare(orangeBox));
    }
}

package Lambda;

public class Lambda {
    public static void main(String[] args) {
        Calculatable<Double> addition = (x, y) -> (x + y);
        Calculatable<Double> subtraction = (x, y) -> (x - y);
        Calculatable<Double> multiplication = (x, y) -> (x * y);
        Calculatable<Double> division = (x, y) -> (x / y);

        Calculatable<Integer> integerAddition = (x, y) -> (x + y);
        Calculatable<String> stringConcatenation = (x, y) -> (x + y);

        System.out.println(integerAddition.calculate(1,1));
        System.out.println(stringConcatenation.calculate("Hello, ", "world!"));
    }


}

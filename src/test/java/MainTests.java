import java.lang.reflect.InvocationTargetException;

public class MainTests {
    public static void main(String[] args) {
        try {
            tests.Runner.start(HelloWorldTest.class);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}

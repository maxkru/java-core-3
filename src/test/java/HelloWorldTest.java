import tests.*;

public class HelloWorldTest {

    HelloWorld hw;

    @BeforeSuite
    public void init() {
        hw = new HelloWorld();
        System.out.println("Tests started.");
    }

    @Test(priority = 9)
    public void test1() {
        System.out.println("Priority 9");
        String actual = hw.hello("Bob");
        String expected = "Hello, Bob!";
        if(!actual.equals(expected))
            throw new TestFailedException(expected, actual);
    }

    @Test(priority = 1)
    public void test2() {
        System.out.println("Priority 1");
        String actual = hw.helloWorld();
        String expected = "Hello, world!";
        if(!actual.equals(expected))
            throw new TestFailedException(expected, actual);
    }

    @AfterSuite
    public void finish() {
        System.out.println("All tests successfully passed.");
    }
}

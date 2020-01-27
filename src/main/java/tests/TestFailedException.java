package tests;

public class TestFailedException extends RuntimeException {
    public TestFailedException(Object expected, Object actual) {
        super("Test failed: \nexpected = " + expected.toString() + "\nactual = " + actual.toString());
    }
}

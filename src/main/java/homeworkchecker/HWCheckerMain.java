package homeworkchecker;

import ru.gb.lesson1.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class HWCheckerMain {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, FileNotFoundException {
        HWChecker checker = new HWChecker(new File("files"), "Main", Main.class);
        checker.printMethodDifference();
        checker.runTests(new File("files/tests.txt"));
    }
}

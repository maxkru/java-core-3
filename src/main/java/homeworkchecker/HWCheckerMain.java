package homeworkchecker;


import ru.gb.lesson1.Main;

import java.io.File;
import java.net.MalformedURLException;

public class HWCheckerMain {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {

        HWChecker checker = new HWChecker(new File("Main.class"), "ru.gb.lesson1.Main", Main.class);
        checker.printMethodDifference();
    }
}

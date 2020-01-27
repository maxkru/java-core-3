package homeworkchecker;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class HWChecker {
    private String fileName;
    private Class<?> tested;
    private Class<? extends ru.gb.lesson1.Main> example;

    public HWChecker(File classFile, String className, Class<? extends ru.gb.lesson1.Main> example) throws MalformedURLException, ClassNotFoundException {
        tested = URLClassLoader.newInstance(new URL[]{classFile.toURI().toURL()}).loadClass(className);
        this.example = example;
        fileName = classFile.getName();
    }

    public void printMethodDifference() {
        for (Method m : example.getMethods()) {
            try {
                Method mT = tested.getMethod(m.getName(), m.getParameterTypes());
                if(!mT.getReturnType().equals(m.getReturnType()))
                    System.out.println(mT.toGenericString() + " returns " + mT.getReturnType() + ", expected " + m.getReturnType());
            } catch (NoSuchMethodException e) {
                System.out.println(m.toGenericString() + " - not found in " + fileName);
            }
        }
        for (Method m : tested.getMethods()) {
            try {
                example.getMethod(m.getName(), m.getParameterTypes());
            } catch (NoSuchMethodException e) {
                System.out.println(m.toGenericString() + " - unspecified method in " + fileName);
            }
        }
    }
    /*
    private boolean invokeSameAndCompare(Method method, Object... args) {
        boolean result = false;
        try {
            Constructor constructorC = tested.getConstructor();
            Constructor constructorEx = example.getConstructor();
            Object objectTested = constructorC.newInstance();
            Object objectExample = constructorEx.newInstance();
            Object resultTest = null;
            Object resultExample = null;
            Throwable throwableTest = null;
            Throwable throwableExample = null;

            method.setAccessible(true);
            try {
                resultTest = method.invoke(objectTested, args);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                throwableTest = e.getTargetException();
            }
            try {
                resultExample = method.invoke(objectExample, args);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                throwableExample = e.getTargetException();
            }
            if((resultTest != null && resultTest.equals(resultExample)) ||
                    (throwableTest != null && throwableExample != null && throwableTest.getClass().equals(throwableExample.getClass()))
                result = true;

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

     */

}

package homeworkchecker;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class HWChecker {
    private String fileName;
    private Class tested;
    private Class example;

    public HWChecker(File classFile, String className, Class example) throws MalformedURLException, ClassNotFoundException {
        tested = URLClassLoader.newInstance(new URL[]{classFile.toURI().toURL()})
                .loadClass(className);
        this.example = example;
        fileName = classFile.getName();
    }

    public void printMethodDifference() {
        for (Method m : example.getDeclaredMethods()) {
            try {
                tested.getDeclaredMethod(m.getName(), m.getParameterTypes());
            } catch (NoSuchMethodException e) {
                System.out.println(m.toGenericString() + " - not found in " + fileName);
            }
        }
        for (Method m : tested.getDeclaredMethods()) {
            try {
                example.getDeclaredMethod(m.getName(), m.getParameterTypes());
            } catch (NoSuchMethodException e) {
                System.out.println(m.toGenericString() + " - unexpected method in " + fileName);
            }
        }
    }

    private boolean invokeSameAndCompare(String methodName, Class[] argsClasses, Object... args) {
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

            Method methodExample = example.getDeclaredMethod(methodName, argsClasses);
            Method methodTested = tested.getDeclaredMethod(methodName, argsClasses);
            methodExample.setAccessible(true);
            methodTested.setAccessible(true);

            try {
                resultTest = methodTested.invoke(objectTested, args);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                throwableTest = e.getTargetException();
            }
            try {
                resultExample = methodExample.invoke(objectExample, args);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                throwableExample = e.getTargetException();
            }
            if((resultTest != null && resultTest.equals(resultExample)) ||
                    (throwableTest != null && throwableExample != null && throwableTest.getClass().equals(throwableExample.getClass())))
                result = true;

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void runTests(File tests) throws FileNotFoundException {
        // формат = "method_name: Class_of_argument1 argument1_value, Class_of_argument2 argument2_value, ..."

        FileReader fileReader = new FileReader(tests);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        try {
            while((line = reader.readLine()) != null) {
                String[] splitColon = line.split(": ", 2);
                String methodName = splitColon[0];
                String argPart = splitColon[1];


                String[] args = argPart.split(", ");
                String[] argsClassesNames = new String[args.length];
                String[] argsValuesAsStrings = new String[args.length];
                for(int i = 0; i < args.length; i++) {
                    String[] t = args[i].split(" ");
                    argsClassesNames[i] = t[0];
                    argsValuesAsStrings[i] = t[1];
                }

                Class[] argsClasses = new Class[argsClassesNames.length];
                for (int i = 0; i < argsClassesNames.length; i++) {
                    argsClasses[i] = parseClass(argsClassesNames[i]);
                }

                Object[] argsValues = new Object[argsValuesAsStrings.length];
                for (int i = 0; i < argsValuesAsStrings.length; i++) {
                    switch (argsClasses[i].getName()) {
                        case "int":
                        case "java.lang.Integer":
                            argsValues[i] = Integer.parseInt(argsValuesAsStrings[i]);
                            break;
                        case "boolean":
                        case "java.lang.Boolean":
                            argsValues[i] = Boolean.getBoolean(argsValuesAsStrings[i]);
                            break;
                        case "float":
                        case "java.lang.Float":
                            argsValues[i] = Float.parseFloat(argsValuesAsStrings[i]);
                            break;
                        default:
                            throw new RuntimeException();
                            // TODO: add more?
                    }
                }

                String resultOfTest = invokeSameAndCompare(methodName, argsClasses, argsValues) ? "OK" : "FAILED";
                System.out.println(line + ": " + resultOfTest);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Class parseClass(String className) {
        Class result;
        switch (className) {
            case "int":
                result = int.class;
                break;
            case "float":
                result = float.class;
                break;
            case "boolean":
                result = boolean.class;
                break;
                // TODO: add more
            default:
                try {
                    result = Class.forName(className);
                } catch (ClassNotFoundException ex) {
                    throw new IllegalArgumentException("Class not found: " + className);
                }
        }
        return result;
    }
}

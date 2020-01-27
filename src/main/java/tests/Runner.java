package tests;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Runner {
    public static void start(Class c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Constructor constructor = c.getConstructor();
        Object obj = constructor.newInstance();
        Method before = null;
        for(Method m : c.getMethods()) {
            if(m.isAnnotationPresent(BeforeSuite.class)) {
                if (before == null)
                    before = m;
                else
                    throw new RuntimeException("More than one @BeforeSuite method");
            }
        }
        Method after = null;
        for(Method m : c.getMethods()) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (after == null)
                    after = m;
                else
                    throw new RuntimeException("More than one @AfterSuite method");
            }
        }

        if(before != null)
            before.invoke(obj);

        ArrayList<Method> testMethodsList = new ArrayList<>();
        for(Method m : c.getMethods())
            if(m.isAnnotationPresent(Test.class))
                testMethodsList.add(m);
        testMethodsList.sort(new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                int result = 0;
                int p1 = m1.getDeclaredAnnotation(Test.class).priority();
                int p2 = m2.getDeclaredAnnotation(Test.class).priority();
                if(p1 > p2)
                    result = 1;
                if(p1 < p2)
                    result = -1;
                return result;
            }
        });
        for(Method m : testMethodsList) {
            m.invoke(obj);
        }

        if(after != null)
            after.invoke(obj);
    }
}

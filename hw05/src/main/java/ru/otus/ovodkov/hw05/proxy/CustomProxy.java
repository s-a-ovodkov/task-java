package ru.otus.ovodkov.hw05.proxy;

import ru.otus.ovodkov.hw05.demo.TestLogging;
import ru.otus.ovodkov.hw05.demo.TestLoggingImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Ovodkov Sergey
 * created on 05.05.2020
 */
public class CustomProxy {

    public static TestLogging createTestLogging() {
        InvocationHandler handler = new TestInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy
                .newProxyInstance(TestLoggingImpl.class.getClassLoader(), new Class<?>[]{TestLogging.class}, handler);
    }

    static class TestInvocationHandler implements InvocationHandler {

        private final TestLogging testLogging;
        private String[] annotationLogMethodNames;

        public TestInvocationHandler(TestLogging testLogging) {
            this.testLogging = testLogging;
            annotationLogMethodNames = Arrays.stream(TestLoggingImpl.class.getDeclaredMethods())
                    .filter(method ->
                            Arrays.stream(method.getDeclaredAnnotations())
                                    .anyMatch(annotation -> annotation.annotationType().getSimpleName().equals("Log"))
                    )
                    .map(Method::getName)
                    .toArray(String[]::new);
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

            boolean isNotLogMethod = Arrays
                    .stream(annotationLogMethodNames)
                    .noneMatch(method.getName()::equals);

            if (isNotLogMethod)
                return method.invoke(testLogging, args);

            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) params.append(", ");
                params.append(args[i]);
            }

            System.out.println("executed method: " + method.getName() + ", params: { " + params + " }");

            return method.invoke(testLogging, args);
        }
    }
}

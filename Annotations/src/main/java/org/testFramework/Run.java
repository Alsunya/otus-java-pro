package org.testFramework;

import java.lang.reflect.Method;

public class Run {
    static Method beforeSuiteMethod = null;
    static Method afterSuiteMethod = null;
    static int successCount = 0;
    static int failCount = 0;

    public static void runTests(Class<?> testClass) {
        Method[] testMethods = testClass.getDeclaredMethods();
        if (!checkType(testMethods)) {
            throw new IllegalArgumentException("Класс не поддерживает запуск тестов");
        }
            try {
                findBorderingAnnotations(testMethods);

                if (beforeSuiteMethod != null) {
                    beforeSuiteMethod.invoke(Tests.class.getConstructor().newInstance());
                }

                executeTests(testMethods);

                if (afterSuiteMethod != null) {
                    afterSuiteMethod.invoke(Tests.class.getConstructor().newInstance());
                }

                printStatistics();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void runTests(String className) throws ClassNotFoundException {
        runTests(Class.forName(className));
    }

    public static boolean checkType(Method[] testMethods){
        boolean isTest = false;
        for (Method method : testMethods) {
            if (method.isAnnotationPresent(Test.class)) {
                isTest = true;
            }
        }
        return isTest;
    }
    public static void findBorderingAnnotations(Method[] testMethods){
        for (Method method : testMethods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethod = method;
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethod = method;
            }
        }
    }
    public static void executeTests(Method[] testMethods){
        for (int i = 1; i <= 10; i++) {
            for (Method method : testMethods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test testAnnotation = method.getAnnotation(Test.class);
                    if (testAnnotation.priority() == i) {
                        try {
                            method.invoke(Tests.class.getConstructor().newInstance());
                            successCount++;
                        } catch (Exception e) {
                            failCount++;
                        }
                    }
                }
            }
        }
    }
    public static void printStatistics(){
        System.out.println("Тесты выполнены!");
        System.out.println("Успешных тестов: " + successCount);
        System.out.println("Упавших тестов: " + failCount);
        System.out.println("Всего тестов: " + (successCount + failCount));
    }
}

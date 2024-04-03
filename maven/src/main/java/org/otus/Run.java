package org.otus;

import java.lang.reflect.Method;

public class Run {
    public static void runTests(Class<?> testClass) {
        Method[] testMethods = testClass.getDeclaredMethods();
        boolean isTest = false;
        for (Method method : testMethods) {
            if (method.isAnnotationPresent(Test.class)) {
                isTest = true;
                break;
            }
        }
        if (!isTest) {
            throw new IllegalArgumentException("Класс не поддерживает запуск тестов");
        } else {
            try {
                Method beforeSuiteMethod = null;
                Method afterSuiteMethod = null;
                int successCount = 0;
                int failCount = 0;

                for (Method method : testMethods) {
                    if (method.isAnnotationPresent(BeforeSuite.class)) {
                        beforeSuiteMethod = method;
                    } else if (method.isAnnotationPresent(AfterSuite.class)) {
                        afterSuiteMethod = method;
                    }
                }

                if (beforeSuiteMethod != null) {
                    beforeSuiteMethod.invoke(Tests.class.getConstructor().newInstance());
                }

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

                if (afterSuiteMethod != null) {
                    afterSuiteMethod.invoke(Tests.class.getConstructor().newInstance());
                }

                System.out.println("Тесты выполнены!");
                System.out.println("Успешных тестов: " + successCount);
                System.out.println("Упавших тестов: " + failCount);
                System.out.println("Всего тестов: " + (successCount + failCount));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void runTests(String className) throws ClassNotFoundException {
        runTests(Class.forName(className));
    }
}

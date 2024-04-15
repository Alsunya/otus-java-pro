package org.otus;

public class Tests {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @Test(priority = 10)
    public void test4() {
        System.out.println("Test 4");
    }

    @Test()
    public void test2() {
        System.out.println("Test 2");
    }

    @Test()
    public void failTest() throws Exception {
        throw new Exception("Тест упал");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("Test 3");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }
}

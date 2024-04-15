package org.tasks;


import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.tasks.TaskAnalyser.*;
import static org.testng.AssertJUnit.assertFalse;

public class AppTest{
    @Test
    void getTasksByStatusTest()
    {
        assertEquals(2, getTasksByStatus(Status.NEW).size());
    }

    @Test
    void isTaskExistTrueTest() {
        assertTrue(isTaskExist(3));
    }

    @Test
    void isTaskExistFalseTest() {
        assertFalse(isTaskExist(10));
    }

    @Test
    void getSortedTasksByStatusTest() {
        assertEquals(Status.NEW, getSortedTasksByStatus().get(0).getStatus());
        assertEquals(Status.DONE, getSortedTasksByStatus().get(5).getStatus());
    }

    @Test
    void getTasksCountByStatusTest(){
        assertEquals(2, getTasksCountByStatus(Status.NEW));
        assertEquals(1, getTasksCountByStatus(Status.IN_PROGRESS));
    }
}

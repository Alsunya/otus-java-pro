package org.tasks;

import static org.tasks.TaskAnalyser.*;

/**
 * TЗ:
 * Имеется список объектов класса Задача (ID, название, статус). С помощью Stream API реализуйте:
 * Получение списка задач по выбранному статусу;
 * Проверка наличия задачи с указанным ID;
 * Получение списка задач в отсортированном по статусу виде: открыта, в работе, закрыта (можете выбирать любой статус и любой порядок, главное чтобы было 3 разных статуса);
 * Подсчет количества задач по определенному статусу.
 */
public class App {
    public static void main(String[] args) {
        System.out.println(getTasksByStatus(Status.NEW).toString());
        System.out.println(isTaskExist(3));
        System.out.println(isTaskExist(10));
        System.out.println(getSortedTasksByStatus().toString());
        System.out.println(getTasksCountByStatus(Status.NEW));
        System.out.println(getTasksCountByStatus(Status.IN_PROGRESS));
    }
}

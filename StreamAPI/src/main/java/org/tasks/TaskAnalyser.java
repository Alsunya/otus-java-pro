package org.tasks;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskAnalyser extends CommonTask {

    /**
     * Получение списка задач по выбранному статусу;
     */
    public static List<Task> getTasksByStatus(Status status) {
        return Task.getTasksStream().filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    /**
     * Проверка наличия задачи с указанным ID;
     */
    public static boolean isTaskExist(int id) {
        return Task.getTasksStream().anyMatch(task -> task.getId() == id);
    }

    /**
     * Получение списка задач в отсортированном по статусу виде:
     * открыта, в работе, закрыта (можете выбирать любой статус и любой порядок, главное чтобы было 3 разных статуса);
     */
    public static List<Task> getSortedTasksByStatus() {
        return Task.getTasksStream().sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

    /**
     * Подсчет количества задач по определенному статусу.
     */
    public static long getTasksCountByStatus(Status status) {
        return Task.getTasksStream().filter(task -> task.getStatus().equals(status))
                .count();

    }
}

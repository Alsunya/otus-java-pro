package org.tasks;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Stream;

abstract class CommonTask {
    @Builder
    protected static class Task {
        @Getter
        private int id;
        @Getter
        @Setter
        private String name;
        @Getter
        @Setter
        private Status status;

        public static Stream<Task> getTasksStream() {
            return Stream.of(
                    Task.builder()
                            .id(1)
                            .name("Сделать уроки")
                            .status(Status.NEW)
                            .build(),
                    Task.builder()
                            .id(2)
                            .name("Погулять с собакой")
                            .status(Status.DONE)
                            .build(),
                    Task.builder()
                            .id(3)
                            .name("Приготовить ужин")
                            .status(Status.IN_PROGRESS)
                            .build(),
                    Task.builder()
                            .id(4)
                            .name("Погладить кота")
                            .status(Status.REVIEW)
                            .build(),
                    Task.builder()
                            .id(5)
                            .name("Съесть суп")
                            .status(Status.NEW)
                            .build(),
                    Task.builder()
                            .id(6)
                            .name("Покормить кота")
                            .status(Status.REVIEW)
                            .build()
            );
        }

        @Override
        public String toString() {
            return "Задача " + id + ": " + name + ". Статус: " + status;
        }
    }
}

package kz.trankwilizator.todo.service;

import kz.trankwilizator.todo.domain.Task;

public class TaskDto {

    private final Task task;

    public TaskDto(Task task){
        this.task = task;
    }

    @Override
    public String toString() {
        return task.getBody();
    }
}

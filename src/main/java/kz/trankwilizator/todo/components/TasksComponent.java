package kz.trankwilizator.todo.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import kz.trankwilizator.todo.domain.Task;
import kz.trankwilizator.todo.repo.TaskRepository;

import java.util.List;

public class TasksComponent extends VerticalLayout {

    private final TaskRepository taskRepository;

    public TasksComponent(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        taskRepository.findAll()
                .stream()
                .map(this::createCBFromTask)
                .forEach(this::add);
    }


    private Checkbox createCBFromTask(Task task) {
        Checkbox c = new Checkbox(task.getBody());
        c.setValue(task.getCompleted());
        c.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Checkbox, Boolean>>)
                checkboxBooleanComponentValueChangeEvent -> {
                    task.setCompleted(checkboxBooleanComponentValueChangeEvent.getValue());
                    taskRepository.save(task);
                });
        c.getStyle().setFontSize("2rem");
        return c;
    }

}

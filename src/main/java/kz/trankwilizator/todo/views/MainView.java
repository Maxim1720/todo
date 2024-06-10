package kz.trankwilizator.todo.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import kz.trankwilizator.todo.components.TaskCreateComponent;
import kz.trankwilizator.todo.components.TasksComponent;
import kz.trankwilizator.todo.domain.Task;
import kz.trankwilizator.todo.repo.TaskRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Route("main")
public class MainView extends HorizontalLayout {

    private final TaskRepository taskRepository;

    private final HorizontalLayout horizontalLayout;

    private TasksComponent tasksComponent;
    private TaskCreateComponent taskCreateComponent;

    public MainView(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        horizontalLayout = new HorizontalLayout();

//        this.setHorizontalComponentAlignment(Alignment.CENTER);
//        this.setAlignItems(Alignment.CENTER);
//        this.setJustifyContentMode(JustifyContentMode.CENTER);

        addItemsToLayout();
        tabs();
        add(horizontalLayout);
    }


    private void addItemsToLayout() {

        tasksComponent = new TasksComponent(
                taskRepository
        );

        taskCreateComponent = new TaskCreateComponent((String text) -> {
            taskRepository.save(
                    Task.builder()
                            .body(text)
                            .completed(false)
                            .build()
            );

            tasksComponent = new TasksComponent(taskRepository);

        });

//        horizontalLayout.add(tasksComponent, taskCreateComponent);


    }

    private void tabs() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        Tab[] tabsArray = new Tab[]{
                new Tab("Tasks"),
                new Tab("Create")
        };

        tabs.add(
                tabsArray
        );

        tabs.addSelectedChangeListener(
                (ComponentEventListener<Tabs.SelectedChangeEvent>) selectedChangeEvent -> {
                    horizontalLayout.removeAll();

                    if (Objects.equals(selectedChangeEvent.getSelectedTab().getLabel(), "Tasks")) {
                        horizontalLayout.add(
                                tasksComponent
                        );
                    } else if (Objects.equals(selectedChangeEvent.getSelectedTab().getLabel(), "Create")) {
                        horizontalLayout.add(
                                taskCreateComponent
                        );
                    }
                }
        );

        tabs.setSelectedIndex(0);
        horizontalLayout.add(
                tasksComponent
        );

        add(
                tabs
        );

    }

}

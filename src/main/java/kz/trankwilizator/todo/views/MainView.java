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

    private final Tabs tabs = new Tabs();

    Tab[] tabsArray = new Tab[]{
            new Tab("Tasks"),
            new Tab("Create")
    };

    public MainView(TaskRepository taskRepository) {

        this.getStyle().setMargin("0");
        this.getStyle().setPadding("2svh 4vw 0 4vw");
        this.getStyle().setHeight("100svh");

        this.taskRepository = taskRepository;
        horizontalLayout = new HorizontalLayout();

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
            changeTab("Tasks");
        });

//        horizontalLayout.add(tasksComponent, taskCreateComponent);


    }

    private void changeTab(String tabLabel){
        horizontalLayout.removeAll();
        if (Objects.equals(tabLabel, "Tasks")) {
            horizontalLayout.add(
                    tasksComponent
            );
        } else if (Objects.equals(tabLabel,"Create")) {
            horizontalLayout.add(
                    taskCreateComponent
            );
        }
        for (int i=0;i<tabsArray.length;i++){
            if(Objects.equals(tabsArray[i].getLabel(), tabLabel)){
                tabs.setSelectedIndex(i);
            }
        }
    }

    private void tabs() {

        tabs.setOrientation(Tabs.Orientation.VERTICAL);



        tabs.add(
                tabsArray
        );

        tabs.addSelectedChangeListener(
                (ComponentEventListener<Tabs.SelectedChangeEvent>) selectedChangeEvent -> {

                    String selected = selectedChangeEvent
                            .getSelectedTab()
                            .getLabel();
                    changeTab(selected);
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

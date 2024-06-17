package kz.trankwilizator.todo.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import kz.trankwilizator.todo.service.Action;

public class TaskCreateComponent extends HorizontalLayout {

    private final TextField bodyTextField;

    private final Button addBtn;

    private final Action<String> performCreate;

    public TaskCreateComponent(Action<String> performCreate) {
        this.bodyTextField = new TextField();
        prepareBodyField();
        addBtn = new Button();
        prepareAddBtn();
        this.performCreate = performCreate;
    }

    private void prepareBodyField() {
        bodyTextField.setPlaceholder("Task title");
        add(bodyTextField);
    }

    private void prepareAddBtn() {
        addBtn.setText("Add");
        addBtn.addClickListener(e -> {
            if (bodyTextField.getValue() != null) {
                performCreate.execute(bodyTextField.getValue());
                bodyTextField.setValue("");
            }
        });
        add(
                addBtn
        );
    }


}

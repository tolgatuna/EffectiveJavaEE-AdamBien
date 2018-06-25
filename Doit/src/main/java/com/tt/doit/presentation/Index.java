package com.tt.doit.presentation;

import com.tt.doit.business.reminders.boundary.TodoManager;
import com.tt.doit.business.reminders.entity.Todo;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Model
public class Index {

    @Inject
    TodoManager todoManager;

    @Inject
    Validator validator;

    Todo todo;

    @PostConstruct
    public void init() {
        this.todo = new Todo();
    }

    public Todo getTodo() {
        return todo;
    }

    public List<Todo> getTodos() {
        return this.todoManager.findAll();
    }

    public void showValidationError(String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
        FacesContext.getCurrentInstance().addMessage("", message);
    }

    public Object save() {
        Set<ConstraintViolation<Todo>> violations = this.validator.validate(this.todo);
        for(ConstraintViolation<Todo> violation:violations) {
            this.showValidationError(violation.getMessage());
        }
        if(violations.isEmpty()) {
            this.todoManager.save(todo);
        }
        return null;
    }
}

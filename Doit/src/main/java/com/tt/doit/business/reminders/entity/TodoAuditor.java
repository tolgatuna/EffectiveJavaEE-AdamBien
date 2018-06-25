package com.tt.doit.business.reminders.entity;

import com.tt.doit.business.reminders.boundary.ChangeEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import static com.tt.doit.business.reminders.boundary.ChangeEvent.Type.CREATION;
import static com.tt.doit.business.reminders.boundary.ChangeEvent.Type.UPDATE;

public class TodoAuditor {

    @Inject @ChangeEvent(CREATION)
    Event<Todo> create;

    @Inject @ChangeEvent(UPDATE)
    Event<Todo> update;

    @PostPersist
    public void onTodoCreate(Todo todo) {
        System.out.println("--------------- todo = " + todo);
        this.create.fire(todo);
    }

    @PostUpdate
    public void onTodoUpdate(Todo todo) {
        System.out.println("--------------- todo = " + todo);
        this.update.fire(todo);
    }
}

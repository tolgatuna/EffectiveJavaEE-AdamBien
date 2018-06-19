package com.tt.doit.business.reminders.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = Todo.findAll, query = "SELECT t FROM Todo t")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Todo {

    @Id
    @GeneratedValue
    private long id;
    private String caption;
    private String description;
    private int priority;
    private boolean done;

    @Version
    private long Version;

    static final String PREFIX = "reminders.entity.Todo";
    public static final String findAll = PREFIX + "findAll";

    public Todo(String caption, String description, int priority) {
        this.caption = caption;
        this.description = description;
        this.priority = priority;
    }

    public Todo() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

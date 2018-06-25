package com.tt.doit.business.reminders.entity;

import com.tt.doit.business.validation.CrossCheck;
import com.tt.doit.business.ValidEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = Todo.findAll, query = "SELECT t FROM Todo t")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CrossCheck
@EntityListeners(TodoAuditor.class)
public class Todo implements ValidEntity {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 5, max = 256 )
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

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    @Override
    public boolean isValid() {
        if(this.priority <= 10)
            return true;

        return (this.description != null && !this.description.isEmpty());
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", done=" + done +
                ", Version=" + Version +
                '}';
    }
}

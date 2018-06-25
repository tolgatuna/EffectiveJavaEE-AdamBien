package com.airhacks.reminders.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Reminder {

    @Id
    private long id;

    private String name;

    public Reminder(String name) {
        this.name = name;
    }

    public Reminder() {
    }
}

package com.airhacks.reminders.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RemindersManager {
    @PersistenceContext
    EntityManager entityManager;


    public String getReminders() {
        return "Called Reminders";
    }
}

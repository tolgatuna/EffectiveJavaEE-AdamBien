package com.airhacks.reminders.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("reminders")
public class RemindersResource {

    @Inject
    RemindersManager remindersManager;

    @GET
    public String getReminders() {
        return this.remindersManager.getReminders();
    }
}

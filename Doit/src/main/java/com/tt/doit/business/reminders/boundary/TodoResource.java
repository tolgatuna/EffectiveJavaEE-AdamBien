package com.tt.doit.business.reminders.boundary;

import com.tt.doit.business.reminders.entity.Todo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("todo")
public class TodoResource {
    @GET
    public Todo hello() {
        return new Todo("implements REST endpoint","...", 100);
    }
}

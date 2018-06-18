package com.tt.doit.business.reminders.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("todo")
public class TodoResource {
    @GET
    public String hello() {
        return "Hello! Time :" + System.currentTimeMillis();
    }
}

package com.tt.doit.business.reminders.boundary;

import com.tt.doit.business.reminders.entity.Todo;

import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class TodoResource {

    long id;
    TodoManager todoManager;

    public TodoResource(long id, TodoManager todoManager) {
        this.id = id;
        this.todoManager = todoManager;
    }

    @GET
    public Todo find() {
        return this.todoManager.findById(id);
    }

    @PUT
    public Todo update(Todo todo) {
        todo.setId(id);
        return this.todoManager.save(todo);
    }

    @DELETE
    public void delete() {
        this.todoManager.delete(id);
    }

    @PUT
    @Path("/status")
    public Response statusUpdate(JsonObject statusUpdate) {
        if(!statusUpdate.containsKey("done"))
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("reason", "Json should contain field done")
                    .build();
        boolean done = statusUpdate.getBoolean("done");
        Todo todo = this.todoManager.updateStatus(id, done);
        if(todo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("reason", "Todo with id (" + id + ") does not exist")
                    .build();
        } else {
            return Response.ok(todo).build();
        }
    }

}

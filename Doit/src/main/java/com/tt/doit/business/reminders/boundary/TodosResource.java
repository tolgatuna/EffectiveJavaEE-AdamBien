package com.tt.doit.business.reminders.boundary;

import com.tt.doit.business.reminders.entity.Todo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Path("todo")
public class TodosResource {
    @Inject
    TodoManager todoManager;

    @Path("{id}")
    public TodoResource find(@PathParam("id") long id) {
        return new TodoResource(id, todoManager);
    }

    @GET
    public List<Todo> all() {
        return this.todoManager.findAll();
    }

    @POST
    public Response save (Todo todo, @Context UriInfo uriInfo) {
        Todo saved = this.todoManager.save(todo);
        long id = saved.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }
}

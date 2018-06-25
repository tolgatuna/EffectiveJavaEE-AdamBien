package com.tt.doit.business.reminder;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TodoResourceIT {

/*
    private Client client;
    private WebTarget webTarget;
*/

    // It just an alternative way to initialize JAX-RS Client
    @Rule
    public JAXRSClientProvider jaxrsClientProvider = JAXRSClientProvider.buildWithURI("http://localhost:16163/Doit/api/todo");

    private static String createdTodoPath ;
    private static boolean isBeforeWorked = false;

/*
    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.webTarget = this.client.target("http://localhost:8083/doit/api/todo");
    }
*/

    @Before
    public void createFirstTodo() {
        if(!isBeforeWorked) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            JsonObject object = objectBuilder.add("caption", "implement first todo item")
                    .add("description", "Description of Todo")
                    .add("priority", 1)
                    .build();
            Response response = this.jaxrsClientProvider.target().request().post(Entity.json(object));
            createdTodoPath = response.getHeaderString("Location");
            System.out.println("Todo is created for test with location : " + createdTodoPath);
            isBeforeWorked = true;
        }
    }

    // Create Todo before tests
    @Test
    public void createTodo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject object = objectBuilder.add("caption", "implement first todo item")
                .add("description", "Description of Todo")
                .add("priority", 1)
                .build();
        Response postResponse = this.jaxrsClientProvider.target().request().post(Entity.json(object));
        assertThat(postResponse.getStatus(), is(201));
        String location = postResponse.getHeaderString("Location");
        System.out.println("Location : " + location);
    }

    // One Todo
    @Test
    public void fetchTodo() {
        Response response = this.jaxrsClientProvider.target().path("1").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonObject readEntity = response.readEntity(JsonObject.class);
        assertTrue(readEntity.getString("caption").startsWith("implement"));
        assertTrue(readEntity.getInt("priority") == 1);
    }
    // Get All Todos
    @Test
    public void fetchAllTodos() {
        Response response = this.jaxrsClientProvider.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray readEntity = response.readEntity(JsonArray.class);
        assertFalse(readEntity.isEmpty());

        JsonObject todo = readEntity.getJsonObject(0);
        assertTrue(todo.getString("caption").startsWith("impl"));
    }

    // Remove Todo
    @Test
    public void removeTodo() {
        Response response = this.jaxrsClientProvider.target().path("1").request(MediaType.TEXT_PLAIN).delete();
        assertThat(response.getStatus(),is(204));
    }

    @Test
    public void updateTodo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject object = objectBuilder.add("caption", "Changed One")
                .add("description","New Description")
                .build();

        Response response = this.jaxrsClientProvider.target(createdTodoPath).request(MediaType.APPLICATION_JSON).put(Entity.json(object));
        JsonObject entity = response.readEntity(JsonObject.class);
        System.out.println(entity.toString());

        assertThat(response.getStatus(), is(200));
        assertTrue(entity.getString("caption").contains("Changed One"));

        // Update Status
        object = objectBuilder.add("done", true).build();
        response = this.jaxrsClientProvider.target(createdTodoPath).path("/status").request(MediaType.APPLICATION_JSON).put(Entity.json(object));
        assertTrue(response.readEntity(JsonObject.class).getBoolean("done") == true);

        object = objectBuilder.add("caption", "Changed Second Time")
            .add("description","New Description")
            .build();
        response = this.jaxrsClientProvider.target(createdTodoPath).request(MediaType.APPLICATION_JSON).put(Entity.json(object));
        assertThat(response.getStatus(), is(409));
        String cause = response.getHeaderString("cause");
        assertNotNull(cause);
        System.out.println("Cause : " + cause);
    }

    @Test
    public void updateNotExistedTodoStatus() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject object = jsonObjectBuilder.add("done", true).build();

        Response response = this.jaxrsClientProvider.target().path("-1/status").request(MediaType.APPLICATION_JSON).put(Entity.json(object));
        assertThat(response.getStatus(), is(400));
        assertFalse(response.getHeaders().isEmpty());
    }

    @Test
    public void updateMalformedStatus() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject object = jsonObjectBuilder.add("malformedPart", true).build();

        Response response = this.jaxrsClientProvider.client().target(createdTodoPath).path("/status").request(MediaType.APPLICATION_JSON).put(Entity.json(object));
        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void createTodoWithoutCaption() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject object = jsonObjectBuilder
                .add("priority", 4)
                .build();

        Response response = this.jaxrsClientProvider.target().request().post(Entity.json(object));
        assertThat(response.getStatus(), is(500 ));

        response.getHeaders().entrySet().forEach(System.out::println);
    }

    @Test
    public void createValidTodo() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject object = jsonObjectBuilder
                .add("priority", 9)
                .add("caption", "12")
                .build();

        Response response = this.jaxrsClientProvider.target().request().post(Entity.json(object));
        assertThat(response.getStatus(), is(201 ));
    }

    @Test
    public void createTodoWithHighPriorityWithoutDescription() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject object = jsonObjectBuilder
                .add("priority", 24)
                .add("caption", "12")
                .build();

        Response response = this.jaxrsClientProvider.target().request().post(Entity.json(object));
        response.getHeaders().entrySet().forEach(System.out::println);
        assertThat(response.getStatus(), is(500 ));
    }

}

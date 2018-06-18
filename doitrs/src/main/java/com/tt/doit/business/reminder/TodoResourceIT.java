package com.tt.doit.business.reminder;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Rule;
import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TodoResourceIT {

/*
    private Client client;
    private WebTarget webTarget;
*/

    // It just an alternative way to initialize JAX-RS Client
    @Rule
    public JAXRSClientProvider jaxrsClientProvider = JAXRSClientProvider.buildWithURI("http://localhost:8083/doit/api/todo");

/*
    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.webTarget = this.client.target("http://localhost:8083/doit/api/todo");
    }
*/

/*    @Test
    public void fetchTodo() {
        Response response = this.jaxrsClientProvider.target().request(MediaType.TEXT_PLAIN).get();
        assertThat(response.getStatus(), is(200));
        String readEntity = response.readEntity(String.class);
        assertTrue(readEntity.startsWith("Hello"));

    }*/

    @Test
    public void fetchTodo() {
        Response response = this.jaxrsClientProvider.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonObject readEntity = response.readEntity(JsonObject.class);
        assertTrue(readEntity.getString("caption").startsWith("implement"));
        assertTrue(readEntity.getInt("priority") == 100);
    }


}

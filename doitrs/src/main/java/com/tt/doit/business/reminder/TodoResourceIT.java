package com.tt.doit.business.reminder;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TodoResourceIT {
    private Client client;
    private WebTarget webTarget;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.webTarget = this.client.target("http://localhost:8083/doit/api/todo");
    }

    @Test
    public void fetchTodo() {
        Response response = this.webTarget.request(MediaType.TEXT_PLAIN).get();
        assertThat(response.getStatus(), is(200));
        String readEntity = response.readEntity(String.class);
        assertTrue(readEntity.startsWith("Hello"));

    }


}

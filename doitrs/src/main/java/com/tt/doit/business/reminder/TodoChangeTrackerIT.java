package com.tt.doit.business.reminder;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObject;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class TodoChangeTrackerIT {

    private WebSocketContainer container;
    private ChangesListener listener;

    @Before
    public void initContainer() throws URISyntaxException, IOException, DeploymentException {
        this.container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://localhost:16163/Doit/changes");
        this.listener = new ChangesListener();
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().decoders(Arrays.asList(JsonDecoder.class)).build();
        this.container.connectToServer(this.listener,config, uri);
    }

    @Test
    public void receiveNotifications() throws InterruptedException {
        JsonObject message = this.listener.getMessage();
        Assert.assertNotNull(message);
        System.out.println("Notification Message :  " + message);
    }



}

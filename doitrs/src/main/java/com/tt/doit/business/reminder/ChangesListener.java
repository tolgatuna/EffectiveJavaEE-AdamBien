package com.tt.doit.business.reminder;


import javax.json.JsonObject;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ChangesListener extends Endpoint {
    JsonObject message;
    CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(JsonObject.class, (msg) -> {
            message = msg;
            latch.countDown();
            System.out.println("Message = " + msg);
        });
    }

    public JsonObject getMessage() throws InterruptedException {
        latch.await(1, TimeUnit.MINUTES);
        return message;
    }
}

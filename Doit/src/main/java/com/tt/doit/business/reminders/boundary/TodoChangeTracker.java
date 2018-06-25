package com.tt.doit.business.reminders.boundary;

import com.tt.doit.business.encoders.JsonEncoder;
import com.tt.doit.business.reminders.entity.Todo;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static com.tt.doit.business.reminders.boundary.ChangeEvent.Type.CREATION;
import static com.tt.doit.business.reminders.boundary.ChangeEvent.Type.UPDATE;

@Singleton
@ServerEndpoint(value = "/changes", encoders = JsonEncoder.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class TodoChangeTracker {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        this.session = null;
    }

    public void onTodoChange(@Observes @ChangeEvent(UPDATE) Todo todo) throws EncodeException {
        System.out.println("############### todo = " + todo);
        if(this.session != null && this.session.isOpen()) {
            try {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                JsonObject object = builder.add("id", todo.getId())
                        .add("Caption", todo.getCaption())
                        .add("cause","update")
                        .add("Priority", todo.getPriority())
                        .add("Description", todo.getDescription())
                        .build();
                this.session.getBasicRemote().sendObject(object);
            } catch (IOException e) {
                // we ignore this.
            }
        }
    }

    public void onTodoCreate(@Observes @ChangeEvent(CREATION) Todo todo) throws EncodeException {
        System.out.println("############### todo = " + todo);
        if(this.session != null && this.session.isOpen()) {
            try {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                JsonObject object = builder.add("id", todo.getId())
                        .add("Caption", todo.getCaption())
                        .add("cause","creation")
                        .add("Priority", todo.getPriority())
                        .add("Description", todo.getDescription())
                        .build();
                this.session.getBasicRemote().sendObject(object);
            } catch (IOException e) {
                // we ignore this.
            }
        }
    }

}

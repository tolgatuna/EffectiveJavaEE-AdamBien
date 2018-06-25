package com.tt.doit.business.encoders;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Writer;

public class JsonEncoder implements Encoder.TextStream<JsonObject> {

    @Override
    public void encode(JsonObject jsonObject, Writer writer) throws EncodeException, IOException {
        try(JsonWriter jsonWriter = Json.createWriter(writer)) {
            jsonWriter.writeObject(jsonObject);
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

package com.tt.doit.business.reminder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Reader;

public class JsonDecoder implements Decoder.TextStream<JsonObject> {
    @Override
    public JsonObject decode(Reader reader) throws DecodeException, IOException {
        try(JsonReader jsonReader = Json.createReader(reader)) {
            return jsonReader.readObject();
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

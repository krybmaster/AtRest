package pflb.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pflb.Json.Deserializer.AuthErrorDeserializer;
import pflb.Json.Serializers.AuthSerializer;
import pflb.Json.Serializers.EmptyReqSerializer;
import pflb.Json.Serializers.InfoSerializer;
import pflb.entity.User;

public class CustomGsonBuilder {


    protected Gson AuthErrorFromJson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new AuthErrorDeserializer())
                .create();
    }

    protected Gson EmptyReqJson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new EmptyReqSerializer())
                .create();
    }

    protected Gson AuthReqJson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new AuthSerializer())
                .create();
    }

    protected Gson InfoReqJson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new InfoSerializer())
                .create();
    }
}

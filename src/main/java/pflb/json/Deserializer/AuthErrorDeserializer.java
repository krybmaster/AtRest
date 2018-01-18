package pflb.json.Deserializer;

import com.google.gson.*;
import pflb.entity.User;

import java.lang.reflect.Type;

public class AuthErrorDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        User user = new User();
        user.setLogin(jsonObject.get("login").getAsString());
        user.setPassMD5(jsonObject.get("passMD5").getAsString());

        return user;
    }
}

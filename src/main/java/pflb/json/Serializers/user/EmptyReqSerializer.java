package pflb.json.Serializers.user;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pflb.entity.user.User;

import java.lang.reflect.Type;

public class EmptyReqSerializer implements JsonSerializer<User>{

    @Override
    public JsonElement serialize(User src, Type typeOfStc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("message", src.getReqMessage());
        result.addProperty("code", src.getReturnCode());

        return result;
    }
}

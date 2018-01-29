package pflb.json.Serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pflb.entity.User;

import java.lang.reflect.Type;

public class InfoSerializer implements JsonSerializer<User>{
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("name", src.getName());
        result.addProperty("last_name", src.getLastName());
        result.addProperty("middle_name", src.getMiddleName());
        result.addProperty("role", src.getRole());
        result.addProperty("img_url", src.getImgURL());
        result.addProperty("message", src.getReqMessage());
        result.addProperty("code", src.getReturnCode());

        return result;
    }
}

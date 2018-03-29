package pflb.json.Serializers.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pflb.entity.user.User;

import java.lang.reflect.Type;

public class InfoSerializer implements JsonSerializer<User>{
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("user_id", src.getUserID());
        result.addProperty("name", src.getName());
        result.addProperty("last_name", src.getLastName());
        result.addProperty("middle_name", src.getMiddleName());
        result.addProperty("role", src.getRole());
        result.addProperty("message", src.getReqMessage());
        result.addProperty("code", src.getReturnCode());
        result.addProperty("co_worker", src.getCoworker());
        result.addProperty("course_id", src.getCurseID());

        return result;
    }
}

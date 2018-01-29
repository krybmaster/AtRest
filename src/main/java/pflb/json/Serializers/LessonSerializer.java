package pflb.json.Serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pflb.entity.Lesson;

import java.lang.reflect.Type;

public class LessonSerializer implements JsonSerializer<Lesson>{
    @Override
    public JsonElement serialize(Lesson src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        result.addProperty("id", src.getID());
        result.addProperty("name", src.getName());
        result.addProperty("lesson_task", src.getLessonTask());
        result.addProperty("home_task", src.getHomeTask());
        result.addProperty("message", src.getReqMessage());
        result.addProperty("code", src.getReturnCode());

        return result;
    }
}

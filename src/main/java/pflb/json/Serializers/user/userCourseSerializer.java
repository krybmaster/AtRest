package pflb.json.Serializers.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pflb.entity.user.Course;

import java.lang.reflect.Type;

public class userCourseSerializer implements JsonSerializer<Course> {
    @Override
    public JsonElement serialize(Course src, Type typeOfSrc, JsonSerializationContext Context) {

        JsonObject result = new JsonObject();

        result.addProperty("id", src.getID());
        result.addProperty("name", src.getName());
        result.addProperty("start_date", src.getStartDate());
        result.addProperty("end_date", src.getEndDate());
        result.addProperty("curr_cource_lesson", src.getCurrCourceLesson());
        result.addProperty("message", src.getReqMessage());
        result.addProperty("code", src.getReturnCode());

        return result;
    }
}

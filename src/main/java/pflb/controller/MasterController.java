package pflb.controller;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.entity.master.*;
import pflb.json.CustomGsonBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static pflb.db.Connection.getConnection;

@RestController
public class MasterController extends CustomGsonBuilder {

    private String json;
    private String sql;
    private String ConType = "Content-Type";
    private String ConValue = "application/json; charset=UTF-8";

    private masterCourse course = new masterCourse();
    private masterLessonWithoutTasks lesson = new masterLessonWithoutTasks();
    private masterTheme theme = new masterTheme();
    private masterFile file = new masterFile();

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getCourses() throws JsonParseException {

        sql = "{call get_courses()}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            ResultSet rs = cstmt.executeQuery();

            List<masterCourse> list = new ArrayList<masterCourse>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String startDate = rs.getDate(3).toString();
                String endDate = rs.getDate(4).toString();
                int number = rs.getShort(5);

                list.add(new masterCourse(id,name,startDate,endDate,number));
            }

            json = new Gson().toJson(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/lessons/course/{ID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getLessonsByCourse(@PathVariable int ID) throws JsonParseException {

        sql = "{call get_lessons_by_course(?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setInt(1,ID);
            ResultSet rs = cstmt.executeQuery();

            List<masterLessonWithoutTasks> list = new ArrayList<masterLessonWithoutTasks>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int number = rs.getShort(3);

                list.add(new masterLessonWithoutTasks(id,name,number));
            }

            json = new Gson().toJson(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/themes/lesson/{ID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getThemesByLesson(@PathVariable int ID) throws JsonParseException {

        sql = "{call get_themes_by_lesson(?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setInt(1,ID);
            ResultSet rs = cstmt.executeQuery();

            List<masterTheme> list = new ArrayList<masterTheme>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                list.add(new masterTheme(id,name));
            }

            json = new Gson().toJson(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/files/theme/{ID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getFilesByTheme(@PathVariable int ID) throws JsonParseException {

        sql = "{call get_files_by_theme(?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setInt(1,ID);
            ResultSet rs = cstmt.executeQuery();

            List<masterFile> list = new ArrayList<masterFile>();
            while (rs.next()) {
                String name = rs.getString(1);
                String url = rs.getString(2);

                list.add(new masterFile(name,url));
            }

            json = new Gson().toJson(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getEmpty() {
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

}

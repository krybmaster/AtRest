package pflb.controller;

import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.entity.user.Course;
import pflb.entity.user.Lesson;
import pflb.json.CustomGsonBuilder;
import pflb.entity.user.User;

import java.sql.*;
import java.util.UUID;

import static pflb.db.Connection.getConnection;

@RestController
public class UserController extends CustomGsonBuilder {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String sql;
    private HttpStatus status;
    private String ConType = "Content-Type";
    private String ConValue = "application/json; charset=UTF-8";

    private String UserID;
    private String Name;
    private String LastName;
    private String MiddleName;
    private int Role;
    private String SessionID;
    private boolean CoWorker;

    private String CourseID;
    private String CourseName;
    private String CourseStartDate;
    private String CourseEndDate;
    private int CourseCurrLesson;

    private int ReturnCode;
    private String ReqMessage;
    private String json;

    private int LessonID;
    private String LessonName;
    private String LessonTask;
    private String HomeTask;

    private User user = new User();
    private Course course = new Course();
    private Lesson lesson = new Lesson();


    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity postAuthUser(@RequestBody String JsonReq) throws JsonParseException {

        ReqMessage = "";
        ReturnCode = 0;

        user = AuthErrorFromJson().fromJson(JsonReq, User.class);

        sql = "{call public.log_in(?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, user.getLogin());
            cstmt.setString(2, user.getPassMD5());
            cstmt.registerOutParameter(3, Types.OTHER);
            cstmt.registerOutParameter(4, Types.SMALLINT);
            cstmt.execute();

            if (null == cstmt.getObject(3)) {
                SessionID = "";
            } else SessionID = cstmt.getObject(3).toString();
            ReturnCode = cstmt.getShort(4);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Login " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 404:
                    status = HttpStatus.NOT_FOUND;
                    ReqMessage = "Login not found";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Login " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 403:
                    status = HttpStatus.FORBIDDEN;
                    ReqMessage = "Access is denied";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Login " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 201:
                    status = HttpStatus.CREATED;
                    ReqMessage = "Session was created";

                    user.setSessionID(SessionID);
                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = AuthReqJson().toJson(user);
                    logger.info("Login " + ReqMessage + " request code: " + ReturnCode + "\n");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(status).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/user/session/{sessionID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getUserInfo(@PathVariable UUID sessionID) throws JsonParseException {

        ReqMessage = "";
        ReturnCode = 0;
        sql = "{call get_info(?,?,?,?,?,?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setObject(1, sessionID,Types.OTHER);
            cstmt.registerOutParameter(2, Types.OTHER); //UserID
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR); //LastName
            cstmt.registerOutParameter(4, java.sql.Types.VARCHAR); //Name
            cstmt.registerOutParameter(5, java.sql.Types.VARCHAR); //MiddleName
            cstmt.registerOutParameter(6, Types.SMALLINT); //Role
            cstmt.registerOutParameter(7, Types.BOOLEAN); // CoWorker
            cstmt.registerOutParameter(8, Types.OTHER); // CourseID*/
            cstmt.registerOutParameter(9, Types.SMALLINT); // ReturnCode
            cstmt.execute();

            UserID = cstmt.getObject(2).toString();
            LastName = cstmt.getString(3);
            Name = cstmt.getString(4);
            MiddleName = cstmt.getString(5);
            Role = cstmt.getShort(6);
            CoWorker = cstmt.getBoolean(7);
            CourseID = cstmt.getObject(8).toString();
            ReturnCode = cstmt.getShort(9);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Get info " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Get info " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 418:
                    status = HttpStatus.I_AM_A_TEAPOT;
                    ReqMessage = "U TEAPOT LUL";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Get info " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    user.setUserID(UserID);
                    user.setCurseID(CourseID);
                    user.setLastName(LastName);
                    user.setLogin("login!!");
                    user.setMiddleName(MiddleName);
                    user.setName(Name);
                    user.setCoworker(CoWorker);
                    user.setImgURL("!!");
                    user.setRole(Role);

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = InfoReqJson().toJson(user);
                    logger.info("Get info " + ReqMessage + " request code: " + ReturnCode + "\n");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/courses/session/{sessionID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getCourses(@PathVariable UUID sessionID) throws JsonParseException {

        ReqMessage = "";
        ReturnCode = 0;
        sql = "{call get_courses(?,?,?,?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setObject(1, sessionID,Types.OTHER);
            cstmt.registerOutParameter(2, Types.OTHER);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.TIMESTAMP);
            cstmt.registerOutParameter(5, Types.TIMESTAMP);
            cstmt.registerOutParameter(6, Types.SMALLINT);
            cstmt.registerOutParameter(7, Types.SMALLINT);
            cstmt.execute();

            CourseID = cstmt.getObject(2).toString();
            CourseName = cstmt.getString(3);
            CourseStartDate = cstmt.getObject(4).toString();
            CourseEndDate = cstmt.getObject(5).toString();
            CourseCurrLesson = cstmt.getInt(6);
            ReturnCode = cstmt.getInt(7);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    course.setReqMessage(ReqMessage);
                    course.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(course);
                    logger.info("Get course " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    course.setReqMessage(ReqMessage);
                    course.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(course);
                    logger.info("Get course " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    course.setID(CourseID);
                    course.setName(CourseName);
                    course.setStartDate(CourseStartDate);
                    course.setEndDate(CourseEndDate);
                    course.setReqMessage(ReqMessage);
                    course.setCurrCourceLesson(CourseCurrLesson);
                    course.setReturnCode(ReturnCode);

                    json = userCourseReqJson().toJson(course);
                    logger.info("Get course " + ReqMessage + " request code: " + ReturnCode + "\n");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/lesson/session/{sessionID}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getLesson(@PathVariable UUID sessionID) throws JsonParseException {

        ReqMessage = "";
        ReturnCode = 0;
        sql = "{call get_lesson(?,?,?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setObject(1, sessionID,Types.OTHER);
            cstmt.registerOutParameter(2, Types.SMALLINT);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.OTHER);
            cstmt.registerOutParameter(5, Types.OTHER);
            cstmt.registerOutParameter(6, Types.SMALLINT);
            cstmt.execute();

            LessonID = cstmt.getShort(2);
            LessonName = cstmt.getString(3);
            ReturnCode = cstmt.getShort(6);

            if (null == cstmt.getObject(4)) {
                LessonTask = "";
            } else LessonTask = cstmt.getObject(4).toString();
            if (null == cstmt.getObject(5)) {
                HomeTask = "";
            } else HomeTask = cstmt.getObject(5).toString();

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    lesson.setReqMessage(ReqMessage);
                    lesson.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(lesson);
                    logger.info("Get course " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    lesson.setReqMessage(ReqMessage);
                    lesson.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(lesson);
                    logger.info("Get course " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    //lesson.setID(CourseID);
                    lesson.setName(CourseName);
                    lesson.setLessonTask(LessonTask);
                    lesson.setHomeTask(HomeTask);
                    lesson.setReqMessage(ReqMessage);
                    lesson.setReturnCode(ReturnCode);

                    json = userCourseReqJson().toJson(lesson);
                    logger.info("Get course " + ReqMessage + " request code: " + ReturnCode + "\n");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).header(ConType, ConValue).body(json);
    }

    @RequestMapping(value = "/auth/session/{sessionID}", method = RequestMethod.DELETE)
    @CrossOrigin
    public ResponseEntity deleteSession(@PathVariable UUID sessionID)   {

        ReqMessage = "";
        ReturnCode = 0;
        sql = "{call public.log_out(?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)){

            cstmt.setObject(1, sessionID,Types.OTHER);
            cstmt.registerOutParameter(2, Types.SMALLINT);
            cstmt.execute();

            ReturnCode = cstmt.getShort(2);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Delete session " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Delete session " + ReqMessage + " error code: " + ReturnCode + "\n");
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    logger.info("Delete session " + ReqMessage + " request code: " + ReturnCode + "\n");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).header(ConType, ConValue).body(json);
    }
}

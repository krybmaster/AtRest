package pflb.controller;

import com.google.gson.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.Json.CustomGsonBuilder;
import pflb.entity.User;

import java.sql.*;

import static pflb.db.Connection.getConnection;

@RestController
public class UserController extends CustomGsonBuilder {

    private String sql;
    private HttpStatus status;

    private String Name;
    private String LastName;
    private String MiddleName;
    private int Role;
    private int ReturnCode;
    private String SessionID;
    private String ReqMessage;
    private String json;

    private User user = new User();

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity postAuthUser(@RequestBody String JsonReq) throws JsonParseException {

        sql = "{CALL dbo.LogIn(?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {
            user = AuthErrorFromJson().fromJson(JsonReq, User.class);

            cstmt.setString(1, user.getLogin());
            cstmt.setString(2, user.getPassMD5());
            cstmt.registerOutParameter(3, java.sql.Types.CHAR);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.execute();

            SessionID = cstmt.getString(3);
            ReturnCode = cstmt.getInt(4);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 404:
                    status = HttpStatus.NOT_FOUND;
                    ReqMessage = "Login not found";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 403:
                    status = HttpStatus.FORBIDDEN;
                    ReqMessage = "Access is denied";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 201:
                    status = HttpStatus.CREATED;
                    ReqMessage = "Session was created";

                    user.setSessionID(SessionID);
                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = AuthReqJson().toJson(user);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).body(json);
    }

    @RequestMapping(value = "/user/session/{sessionID}", method = RequestMethod.GET)
    public ResponseEntity getUserInfo(@PathVariable String sessionID) throws JsonParseException {

        sql = "{CALL dbo.GetUser(?,?,?,?,?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, sessionID);
            cstmt.registerOutParameter(2, Types.CHAR);
            cstmt.registerOutParameter(3, Types.CHAR);
            cstmt.registerOutParameter(4, Types.CHAR);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.execute();

            Name = cstmt.getString(2);
            LastName = cstmt.getString(3);;
            MiddleName = cstmt.getString(4);
            Role = cstmt.getInt(5);
            ReturnCode = cstmt.getInt(6);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 418:
                    status = HttpStatus.I_AM_A_TEAPOT;
                    ReqMessage = "U TEAPOT LUL";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    user.setName(Name);
                    user.setLastName(LastName);
                    user.setMiddleName(MiddleName);
                    user.setRole(Role);
                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = InfoReqJson().toJson(user);
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).body(json);
    }

    @RequestMapping(value = "/auth/session/{sessionID}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSession(@PathVariable String sessionID) {

        sql = "{CALL dbo.LogOut(?,?)}";

        try (Connection con = getConnection();
             CallableStatement cstmt = con.prepareCall(sql)){

            cstmt.setString(1, sessionID);
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.execute();

            ReturnCode = cstmt.getInt(2);

            switch (ReturnCode) {
                case 500:
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                    ReqMessage = "Server error";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 401:
                    status = HttpStatus.UNAUTHORIZED;
                    ReqMessage = "User unauthorized";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
                case 200:
                    status = HttpStatus.OK;
                    ReqMessage = "OK";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = EmptyReqJson().toJson(user);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).body(json);
    }


}

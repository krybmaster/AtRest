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

                    json = AuthReqJsonWithError().toJson(user);
                    break;
                case 404:
                    status = HttpStatus.NOT_FOUND;
                    ReqMessage = "Login not found";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = AuthReqJsonWithError().toJson(user);
                    break;
                case 403:
                    status = HttpStatus.FORBIDDEN;
                    ReqMessage = "Access is denied";

                    user.setReqMessage(ReqMessage);
                    user.setReturnCode(ReturnCode);

                    json = AuthReqJsonWithError().toJson(user);
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

        sql = "{CALL dbo.GetUser(?)}";
        String infoReq = "";
        int role = 0;
        String login = "";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareCall(sql);
            pstmt.setString(1, sessionID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                role = rs.getInt("ROLE");
                login = rs.getString("LOGIN");
            }
            if (role == 0 | login == null) {
//                return infoReq = "404";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ignored) {
            }
            try {
                pstmt.close();
            } catch (SQLException ignored) {
            }
            try {
                con.close();
            } catch (SQLException ignored) {
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(infoReq);
    }

    @RequestMapping(value = "/auth/session/{sessionID}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSession(@PathVariable String sessionID) {

        sql = "{CALL dbo.LogOut(?)}";
        int resultCode = 200;

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareCall(sql);
            pstmt.setString(1, sessionID);
            resultCode = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException ignored) {
            }
            try {
                con.close();
            } catch (SQLException ignored) {
            }
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}

package pflb.controller;

import com.google.gson.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.db.*;
import pflb.entity.Session;
import pflb.entity.UserForLogin;
import pflb.entity.UserInfo;

import java.sql.*;
import java.sql.Connection;

import static pflb.db.Connection.getConnection;

@RestController
public class UserController {


    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private String sql;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity postAuthUser(@RequestBody String JsonReq) throws JsonParseException {

        sql = "{CALL dbo.LogIn(?,?)}";
        String sessionIDJson = "";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            UserForLogin user = GSON.fromJson(JsonReq, UserForLogin.class);

            pstmt = con.prepareCall(sql);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassMD5());
            rs = pstmt.executeQuery();
            rs.next();

            //switch ()

            Session sessionReq = new Session(rs.getString("SESSION_ID"));
            sessionIDJson = GSON.toJson(sessionReq);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {rs.close();} catch (SQLException ignored) {}
            try {pstmt.close();} catch (SQLException ignored) {}
            try {con.close();} catch (SQLException ignored) {}
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionIDJson);
    }

    @RequestMapping(value = "/user/session/{sessionID}", method = RequestMethod.GET)
    public ResponseEntity getUserInfo(@PathVariable String sessionID) throws JsonParseException{

        sql = "{CALL dbo.GetUser(?)}";
        String infoReq = "";
        int role = 0;
        String login = "";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareCall(sql);
            pstmt.setString(1,sessionID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                role = rs.getInt("ROLE");
                login = rs.getString("LOGIN");
            }
            if (role == 0 | login == null) {
//                return infoReq = "404";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                UserInfo user = new UserInfo(login, role);
                infoReq = GSON.toJson(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {rs.close();} catch (SQLException ignored) {}
            try {pstmt.close();} catch (SQLException ignored) {}
            try {con.close();} catch (SQLException ignored) {}
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(infoReq);
    }

    @RequestMapping(value = "/auth/session/{sessionID}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSession(@PathVariable String sessionID){

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
            try {pstmt.close();} catch (SQLException ignored) {}
            try {con.close();} catch (SQLException ignored) {}
        }



        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}

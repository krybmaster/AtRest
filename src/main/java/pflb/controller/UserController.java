package pflb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.entity.User;
import pflb.entity.UserForLogin;

import java.sql.*;
import java.util.List;

import static pflb.db.Connection.getConnection;

@RestController
public class UserController {

    private int ResultCode;
    private String sql;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public int postAuthUser(@RequestBody UserForLogin user) {

        sql = "{CALL dbo.LogIn(?,?)}";
        Connection con = null;
        con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sessionID = "";

        try {
            pstmt = con.prepareCall(sql);
            pstmt.setString(1, user.getLOGIN());
            pstmt.setString(2, user.getPASSMD5());
            rs = pstmt.executeQuery();
            rs.next();

            sessionID = rs.getString("SESSION_ID");
            if (sessionID == "") ResultCode = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {rs.close();} catch (SQLException ignored) {}
            try {pstmt.close();} catch (SQLException ignored) {}
            try {con.close();} catch (SQLException ignored) {}
        }
        return ResultCode;
    }

    @RequestMapping(value = "/user/session/{id}", method = RequestMethod.GET)
    public int getUserInfo(@PathVariable String id){
        //sql запрос на получение информации о пользователе
        return ResultCode;
    }

    @RequestMapping(value = "/auth/session/{login}", method = RequestMethod.DELETE)
    public int deleteSession(@PathVariable String login){

        sql = "{CALL dbo.LogOut(?)}";
        Connection con = null;
        con = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareCall(sql);
            pstmt.setString(1, login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ResultCode = 1;
        } finally {
            try {pstmt.close();} catch (SQLException ignored) {}
            try {con.close();} catch (SQLException ignored) {}
        }

        return ResultCode;
    }


}

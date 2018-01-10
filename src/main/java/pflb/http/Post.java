package pflb.http;

import pflb.db.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post extends Connection{

    java.sql.Connection con = getConnection();
    PreparedStatement pstmt;
    ResultSet rs;

    public void getParamsForLogin(java.sql.Connection con, String login, String password) {

        String query = "CREATE PROCEDURE dbo.authUser" +
                "@Login nvarchar(30)" +
                "@Password nvarchar(30)" +
                "AS SELECT * FROM users" +
                "WHERE LOGIN = @Login AND PASSMD5 = @Password" +
                "GO";

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,"");

            pstmt.executeUpdate();
            rs = pstmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

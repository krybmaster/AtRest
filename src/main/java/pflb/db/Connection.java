package pflb.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection implements GetConnection{

    public static java.sql.Connection getConnection(){

        java.sql.Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(conUrl, user, pass);
            stmt = con.createStatement();
        } catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return con;
    }
}

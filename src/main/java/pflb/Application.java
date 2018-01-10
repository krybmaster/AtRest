package pflb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.*;

import static pflb.db.Connection.getConnection;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs;
        String query = "SELECT LOGIN FROM users";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                String login = rs.getString("LOGIN");

                System.out.println("Login: " + login);
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    con.close();
            }catch(SQLException se){}
            try{
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        //SpringApplication.run(Application.class, args);
    }
}

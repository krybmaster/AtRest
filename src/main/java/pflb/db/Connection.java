package pflb.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pflb.controller.UserController;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection implements GetConnection {

    public static java.sql.Connection getConnection() {

        final Logger logger = LogManager.getLogger(UserController.class);

        java.sql.Connection con = null;

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //con = DriverManager.getConnection(MsSqlConUrl, MsSqlUser, MsSqlPass);
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(PgSqlConUrl,PgSqlUser,PgSqlPass);
            logger.info("Connection to db successful");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return con;
    }
}

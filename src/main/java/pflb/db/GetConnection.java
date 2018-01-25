package pflb.db;

public interface GetConnection {

    String MsSqlConUrl = "jdbc:sqlserver://192.168.81.16\\sqlexpress;databaseName=pflb;";
    String MsSqlUser = "y.ryabov";
    String MsSqlPass = "82654";

    String PgSqlConUrl = "jdbc:postgresql://192.168.81.16/PFLB";
    String PgSqlUser = "y.ryabov";
    String PgSqlPass = "82654";
}

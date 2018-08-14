package ru.progmatik.main.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DBService {
    private static DBService dbService;
    private static Connection connection;
    private static Properties props;

//    @Value("${driver:org.firebirdsql.jdbc.FBDriver}")
//    private String driver;
//
//    @Value("${databaseurl}")
//    private String databaseurl;
//
//    @Value("${username:SYSDBA}")
//    private String username;
//
//    @Value("${password:masterkey}")
//    private String password;
//
//    @Value("${role:}")
//    private String role;
//
//    @Value("${encoding:WIN1251}")
//    private String encoding;

    public static DBService instance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if(dbService == null){
            dbService = new DBService();
        }
        return dbService;
    }
    private DBService() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        DriverManager.registerDriver((Driver) Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance());
        props = new Properties();

        props.setProperty("userName", "SYSDBA");
        props.setProperty("password", "111");
//            if(!role.isEmpty()) props.setProperty("role", role);
//            if(!encoding.isEmpty())
        props.setProperty("encoding", "WIN1251");

        getConnection();
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:firebirdsql:172.16.0.18:/opt/firebird/bases/FIAS.fdb", props);
        }
        return connection;
    }
}
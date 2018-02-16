package server;

import lombok.extern.java.Log;
import services.InfoObject;
import services.ServerObject;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.registerDriver;

@Log
public class DataBase {

    public static Connection connect(String url, String userName, String password) {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            registerDriver(driver);

            connection = getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }

    }

    public static void writeData(ServerObject object, InfoObject infoObject) throws SQLException {

        DateFormat formatDate = new SimpleDateFormat("yyyy-MMMM-d HH:mm:ss", Locale.ENGLISH);
        Date currentTime = Calendar.getInstance().getTime();

        String url = "jdbc:mysql://" + object.getHost() + ":3306/" + object.getDbName();
        infoObject.setData(formatDate.format(currentTime));

        Connection connection = connect(url, object.getUser(), object.getPassword());

        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO " + object.getDbName() + "." + object.getTable() + " VALUES " +
                    "(" +
                    "'" + infoObject.getData() + "'" + ", " +
                    "'" + infoObject.getTemp() + "'" + ", " +
                    "'" + infoObject.getCo2() + "'"
                    + ")");
            log.info("Data was written to db");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}


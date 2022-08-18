package axal25.oles.jacek.TDDDemo.utils.data.source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceUtils {

    private DataSourceUtils() {
    }

    public static ResultSet getCatalogs(DataSource dataSource) {
        DatabaseMetaData databaseMetaData = getDatabaseMetaData(dataSource);
        ResultSet Catalogs = null;
        try {
            Catalogs = databaseMetaData != null ? databaseMetaData.getCatalogs() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Catalogs;
    }

    public static String getDriverVersion(DataSource dataSource) {
        DatabaseMetaData databaseMetaData = getDatabaseMetaData(dataSource);
        String driverVersion = null;
        try {
            driverVersion = databaseMetaData != null ? databaseMetaData.getDriverVersion() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverVersion;
    }

    public static String getDriverName(DataSource dataSource) {
        DatabaseMetaData databaseMetaData = getDatabaseMetaData(dataSource);
        String driverName = null;
        try {
            driverName = databaseMetaData != null ? databaseMetaData.getDriverName() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverName;
    }

    public static DatabaseMetaData getDatabaseMetaData(DataSource dataSource) {
        Connection connection = getConnection(dataSource);
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = connection != null ? connection.getMetaData() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseMetaData;
    }

    public static String getCatalog(DataSource dataSource) {
        Connection connection = getConnection(dataSource);
        String catalog = null;
        try {
            catalog = connection != null ? connection.getCatalog() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalog;
    }

    public static String getSchema(DataSource dataSource) {
        Connection connection = getConnection(dataSource);
        String schema = null;
        try {
            schema = connection != null ? connection.getSchema() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schema;
    }

    public static Connection getConnection(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource != null ? dataSource.getConnection() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

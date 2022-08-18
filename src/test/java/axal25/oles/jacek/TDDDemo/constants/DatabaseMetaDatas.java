package axal25.oles.jacek.TDDDemo.constants;

import com.zaxxer.hikari.pool.HikariProxyDatabaseMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DatabaseMetaDatas {
    HIKARI_PROXY(
            "jdbc:oracle:thin:@localhost:1521/ecommerce.localdomain",
            "Oracle",
            "Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production",
            12,
            2,
            HikariProxyDatabaseMetaData.class.getName(),
            "oracle.jdbc.driver.OracleDatabaseMetaData",
            SqlStates.ORACLE_DATABASE_META_DATA_CLASS_CONST_RETURN_FOR_METHOD_GET_SQL_STATE_TYPE.getValue(),
            "Oracle JDBC driver",
            "21.3.0.0.0",
            21,
            3,
            4,
            2
    ),
    ORACLE(
            HIKARI_PROXY.url,
            HIKARI_PROXY.databaseProductName,
            HIKARI_PROXY.databaseProductVersion,
            HIKARI_PROXY.databaseMajorVersion,
            HIKARI_PROXY.databaseMinorVersion,
            HIKARI_PROXY.delegateClassName,
            HIKARI_PROXY.delegateClassName,
            HIKARI_PROXY.sqlStateValue,
            HIKARI_PROXY.driverName,
            HIKARI_PROXY.driverVersion,
            HIKARI_PROXY.driverMajorVersion,
            HIKARI_PROXY.driverMinorVersion,
            HIKARI_PROXY.jdbcMajorVersion,
            HIKARI_PROXY.jdbcMinorVersion
    );

    public static final int MAX_LIMIT_UNKNOWN = 0;

    private final String url;
    private final String databaseProductName;
    private final String databaseProductVersion;
    private final int databaseMajorVersion;
    private final int databaseMinorVersion;

    private final String className;
    private final String delegateClassName;

    private final int sqlStateValue;

    private final String driverName;
    private final String driverVersion;
    private final int driverMajorVersion;
    private final int driverMinorVersion;

    private final int jdbcMajorVersion;
    private final int jdbcMinorVersion;
}
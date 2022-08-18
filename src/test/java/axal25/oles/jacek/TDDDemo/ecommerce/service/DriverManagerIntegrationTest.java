package axal25.oles.jacek.TDDDemo.ecommerce.service;

import axal25.oles.jacek.TDDDemo.constants.Connections;
import axal25.oles.jacek.TDDDemo.constants.DatabaseMetaDatas;
import axal25.oles.jacek.TDDDemo.health.DataSourceIntegrationTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("DriverManager (Connection) Integration Tests")
public class DriverManagerIntegrationTest {

    @Test
    @Order(1)
    public void datasource_returnsOracleT4C() throws SQLException {
        Connection connection = getDriverManagerConnection();
        assertThat(connection, is(notNullValue()));

        assertThat(
                String.format(
                        DataSourceIntegrationTest.ConnectionDependant.MSG_CON_NOT_MATCH_PATTERN,
                        connection,
                        Connections.ORACLE_T4C.getToStringPattern()
                ),
                Connections.ORACLE_T4C.getToStringPattern()
                        .matcher(connection.toString())
                        .matches(),
                is(equalTo(true))
        );
    }

    @Test
    @Order(2)
    public void datasource_returnsNotHikariProxy() throws SQLException {
        Connection connection = getDriverManagerConnection();
        assertThat(connection, is(notNullValue()));

        assertThat(
                String.format(
                        DataSourceIntegrationTest.ConnectionDependant.MSG_CON_NOT_MATCH_PATTERN,
                        connection,
                        Connections.HIKARI_PROXY.getToStringPattern()
                ),
                Connections.HIKARI_PROXY.getToStringPattern()
                        .matcher(connection.toString())
                        .matches(),
                is(equalTo(false)));
    }

    // TODO: get user and password from env vars
    private Connection getDriverManagerConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseMetaDatas.ORACLE.getUrl(),
                "ecommerce",
                "ecommerce_main_Pwd_1234567890"
        );
    }
}

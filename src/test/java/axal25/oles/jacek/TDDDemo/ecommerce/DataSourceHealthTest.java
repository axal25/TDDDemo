package axal25.oles.jacek.TDDDemo.ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataSourceHealthTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void dataSourceIsOk() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = connection != null ? connection.getMetaData() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String catalog = null;
        try {
            catalog = connection != null ? connection.getCatalog() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(databaseMetaData, is(notNullValue()));
        assertThat(catalog, is(equalTo("spring-tdd")));
    }
}

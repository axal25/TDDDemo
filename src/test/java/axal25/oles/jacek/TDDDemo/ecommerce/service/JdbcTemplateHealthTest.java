package axal25.oles.jacek.TDDDemo.ecommerce.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("JdbcTemplate Health Unit Tests")
public class JdbcTemplateHealthTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DataSource jdbcTemplateDataSource;

    @Test
    @Order(1)
    public void dataSource_isNotNull() {
        assertThat(dataSource, is(notNullValue()));
    }

    @Test
    @Order(2)
    public void databaseConfig_isNotNull() {
        assertThat(jdbcTemplate, is(notNullValue()));
    }

    @Test
    @Order(3)
    public void databaseConfig_isLazyInit_returnsExpected() {
        assertThat(jdbcTemplate.isLazyInit(), is(equalTo(true)));
    }

    @Test
    @Order(4)
    public void jdbcTemplateDataSource_isNotNull() {
        jdbcTemplateDataSource = jdbcTemplate.getDataSource();
        assertThat(jdbcTemplateDataSource, is(notNullValue()));
    }

    @Test
    @Order(5)
    public void databaseConfig_returnsExpected() {
        assertThat(jdbcTemplateDataSource, is(equalTo(dataSource)));
    }
}

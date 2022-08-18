package axal25.oles.jacek.TDDDemo.utils.data.source;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("DataSourceUtils Unit Tests")
public class DataSourceUtilsUnitTest {

    @Mock
    private DataSource mockDataSource;
    @Mock
    private Connection mockConnection;
    @Mock
    private DatabaseMetaData mockDatabaseMetaData;
    @Mock
    private ResultSet mockResultSet;

    @Captor
    private ArgumentCaptor<DataSource> acDataSource;

    @BeforeEach
    public void beforeEach() {
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("getConnection tests")
    public class GetConnection {

        @Test
        @Order(1)
        @DisplayName("getConnection returns expected Connection")
        public void getConnection_ReturnsExpectedConnection() throws SQLException {
            when(mockDataSource.getConnection()).thenReturn(mockConnection);

            Connection actualConnection = DataSourceUtils.getConnection(mockDataSource);

            assertThat(actualConnection, is(notNullValue()));
            assertThat(actualConnection, is(equalTo(mockConnection)));
        }

        @Test
        @Order(2)
        @DisplayName("getConnection returns null because DataSource is null")
        public void getConnection_ReturnsNull_BecauseDataSourceIsNull() {
            Connection actualConnection = DataSourceUtils.getConnection(null);

            assertThat(actualConnection, is(nullValue()));
        }

        @Test
        @Order(3)
        @DisplayName("getConnection returns null because DataSource throws SQLException")
        public void getConnection_ReturnsNull_becauseDataSourceThrowsSQLException() throws SQLException {
            when(mockDataSource.getConnection())
                    .thenThrow(new SQLException("getConnection returns null because DataSource throws SQLException"));

            Connection actualConnection = DataSourceUtils.getConnection(mockDataSource);

            assertThat(actualConnection, is(nullValue()));
        }
    }

    // TODO: used to work - broke at some point
    /**
     @Nested
     @TestInstance(TestInstance.Lifecycle.PER_CLASS)
     @DisplayName("getCatalogs tests")
     public class GetCatalogs {

     @Test
     @Order(1)
     @DisplayName("getCatalogs returns expected Catalogs")
     public void getCatalogs_ReturnsExpectedCatalogs() throws SQLException {
     try (MockedStatic<DataSourceUtils> mockDataSourceUtils = mockStatic(DataSourceUtils.class)) {
     mockDataSourceUtils.when(() -> DataSourceUtils.getCatalogs(ArgumentMatchers.any()))
     .thenCallRealMethod();
     mockDataSourceUtils.when(() -> DataSourceUtils.getDatabaseMetaData(ArgumentMatchers.any()))
     .thenReturn(mockDatabaseMetaData);
     when(mockDatabaseMetaData.getCatalogs()).thenReturn(mockResultSet);

     ResultSet actualCatalogs = DataSourceUtils.getCatalogs(mockDataSource);

     verify(mockDatabaseMetaData, only()).getCatalogs();
     verifyNoMoreInteractions(mockDatabaseMetaData);

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getCatalogs(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getDatabaseMetaData(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     assertThat(actualCatalogs, is(notNullValue()));
     assertThat(actualCatalogs, is(equalTo(mockResultSet)));

     mockDataSourceUtils.verifyNoMoreInteractions();
     }
     }

     @Test
     @Order(2)
     @DisplayName("getCatalogs returns null because DatabaseMetaData is null")
     public void getCatalogs_ReturnsNull_BecauseDatabaseMetaDataIsNull() {
     try (MockedStatic<DataSourceUtils> mockDataSourceUtils = mockStatic(DataSourceUtils.class)) {
     mockDataSourceUtils.when(() -> DataSourceUtils.getCatalogs(ArgumentMatchers.any()))
     .thenCallRealMethod();
     mockDataSourceUtils.when(() -> DataSourceUtils.getDatabaseMetaData(ArgumentMatchers.isNull()))
     .thenReturn(null);

     ResultSet actualCatalogs = DataSourceUtils.getCatalogs(null);

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getCatalogs(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(nullValue()));

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getDatabaseMetaData(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(nullValue()));

     assertThat(actualCatalogs, is(nullValue()));

     mockDataSourceUtils.verifyNoMoreInteractions();
     }
     }

     @Test
     @Order(3)
     @DisplayName("getCatalogs returns null because DatabaseMetaData returns null")
     public void getCatalogs_ReturnsNull_becauseDatabaseMetaDataReturnsNull() {
     try (MockedStatic<DataSourceUtils> mockDataSourceUtils = mockStatic(DataSourceUtils.class)) {
     mockDataSourceUtils.when(() -> DataSourceUtils.getCatalogs(ArgumentMatchers.any()))
     .thenCallRealMethod();
     mockDataSourceUtils.when(() -> DataSourceUtils.getDatabaseMetaData(ArgumentMatchers.any()))
     .thenReturn(null);

     ResultSet actualCatalogs = DataSourceUtils.getCatalogs(mockDataSource);

     verifyNoInteractions(mockDatabaseMetaData);

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getCatalogs(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getDatabaseMetaData(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     assertThat(actualCatalogs, is(nullValue()));

     mockDataSourceUtils.verifyNoMoreInteractions();
     }
     }

     @Test
     @Order(4)
     @DisplayName("getCatalogs returns null because DatabaseMetaData throws SQLException")
     public void getCatalogs_ReturnsNull_becauseDatabaseMetaDataThrowsSQLException() throws SQLException {
     try (MockedStatic<DataSourceUtils> mockDataSourceUtils = mockStatic(DataSourceUtils.class)) {
     mockDataSourceUtils.when(() -> DataSourceUtils.getCatalogs(ArgumentMatchers.any()))
     .thenCallRealMethod();
     mockDataSourceUtils.when(() -> DataSourceUtils.getDatabaseMetaData(ArgumentMatchers.any()))
     .thenReturn(mockDatabaseMetaData);
     when(mockDatabaseMetaData.getCatalogs())
     .thenThrow(
     new SQLException("getCatalogs returns null because DatabaseMetaData throws SQLException"));

     ResultSet actualCatalogs = DataSourceUtils.getCatalogs(mockDataSource);

     verify(mockDatabaseMetaData, only()).getCatalogs();
     verifyNoMoreInteractions(mockDatabaseMetaData);

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getCatalogs(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     mockDataSourceUtils.verify(
     () -> DataSourceUtils.getDatabaseMetaData(acDataSource.capture()),
     times(1));

     assertThat(acDataSource.getValue(), is(notNullValue()));
     assertThat(acDataSource.getValue(), is(equalTo(mockDataSource)));

     assertThat(actualCatalogs, is(nullValue()));

     mockDataSourceUtils.verifyNoMoreInteractions();
     }
     }
     }
     **/
}

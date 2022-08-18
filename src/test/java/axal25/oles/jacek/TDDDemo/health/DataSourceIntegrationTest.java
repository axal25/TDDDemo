package axal25.oles.jacek.TDDDemo.health;

import axal25.oles.jacek.TDDDemo.constants.*;
import com.zaxxer.hikari.HikariDataSource;
import oracle.jdbc.OracleConnection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("DataSource Integration Tests")
public class DataSourceIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @Order(1)
    public void datasource_isNotNull() {
        assertThat(dataSource, is(notNullValue()));
    }

    @Test
    @Order(2)
    public void datasource_equalsNotHikariDefault() {
        assertThat(
                DataSources.HIKARI_DEFAULT.getToStringPattern()
                        .matcher(dataSource.toString())
                        .matches(),
                is(equalTo(false)));
    }

    @Test
    @Order(3)
    public void datasource_equalsNotSpringDriverManager() {
        assertThat(
                DataSources.SPRING_DRIVER_MANAGER.getToStringPattern()
                        .matcher(dataSource.toString())
                        .matches(),
                is(equalTo(false)));
    }

    @Test
    @Order(4)
    public void datasource_equalsHikariEcommerce() {
        assertThat(
                DataSources.HIKARI_ECOMMERCE.getToStringPattern()
                        .matcher(dataSource.toString())
                        .matches(),
                is(equalTo(true)));
    }

    @Test
    @Order(5)
    public void datasource_classEquals() {
        assertThat(dataSource.getClass(), is(equalTo(HikariDataSource.class)));
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class ConnectionDependant {
        public static final String MSG_CON_NOT_MATCH_PATTERN = "Connection#toString() equal to \"%s\" did not match pattern \"%s\"";

        private Connection connection;

        @BeforeEach
        public void beforeEach() throws SQLException {
            connection = dataSource.getConnection();
        }

        @Test
        @Order(1)
        public void connection_isNotNull() {
            assertThat(connection, is(notNullValue()));
        }

        @Test
        @Order(2)
        public void connection_equalsHikariProxy() {
            assertThat(
                    String.format(MSG_CON_NOT_MATCH_PATTERN,
                            connection,
                            Connections.HIKARI_PROXY.getToStringPattern()),
                    Connections.HIKARI_PROXY.getToStringPattern()
                            .matcher(connection.toString())
                            .matches(),
                    is(equalTo(true)));
        }

        @Test
        @Order(3)
        public void connection_equalsNotSpringDriverManager() {
            assertThat(
                    String.format(MSG_CON_NOT_MATCH_PATTERN,
                            connection,
                            Connections.ORACLE_T4C.getToStringPattern()),
                    Connections.ORACLE_T4C.getToStringPattern()
                            .matcher(connection.toString())
                            .matches(),
                    is(equalTo(false)));
        }

        @Test
        @Order(4)
        public void schema_equalsEcommerce() throws SQLException {
            String schema = connection.getSchema();
            assertThat(schema, is(notNullValue()));
            schema = schema.toUpperCase();
            assertThat(schema, is(equalTo(Schemas.ECOMMERCE.getName())));
        }

        @Test
        @Order(5)
        public void autoCommit_equalsTrue() throws SQLException {
            assertThat(connection.getAutoCommit(), is(equalTo(true)));
        }

        @Test
        @Order(6)
        void catalog_equalsExpected() throws SQLException {
            String catalog = connection.getCatalog();
            assertThat(catalog, is(nullValue()));
            assertThat(catalog, is(equalTo(Catalogs.NULL.getName())));
        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        public class TestQueries {

            @Test
            @Order(1)
            void execute_validationQuery_executedEqualsTrue() throws SQLException {
                Boolean isExecuted = connection.createStatement().execute(SqlQueries.VALIDATION_QUERY.getQuery());
                assertThat(isExecuted, is(true));
            }


            @Test
            @Order(2)
            void executeQuery_validationQuery_returnsExpected() throws SQLException {
                ResultSet resultSet = connection.createStatement().executeQuery(SqlQueries.VALIDATION_QUERY.getQuery());
                assertThat(resultSet, is(notNullValue()));
                // TODO: fix, check something
                //  assertThat(resultSet, is(equalTo(SqlQueries.VALIDATION_QUERY.getExpectedResult())));
            }

            @Test
            @Order(3)
            void execute_databaseValidationQuery_executedTrue() throws SQLException {
                // TODO: fix, check something
                //  Boolean isExecuted = connection.createStatement().execute(SqlQueries.DATABASE_VALIDATION.getQuery());
                //  assertThat(isExecuted, is(true));
            }


            @Test
            @Order(4)
            void executeQuery_databaseValidationQuery_returnsExpected() throws SQLException {
                // TODO: fix, check something
                //  ResultSet resultSet = connection.createStatement().executeQuery(SqlQueries.DATABASE_VALIDATION.getQuery());
                //  assertThat(resultSet, is(notNullValue()));
                //  assertThat(resultSet, is(equalTo(SqlQueries.DATABASE_VALIDATION.getExpectedResult())));
            }

            @Test
            @Order(5)
            void execute_query_executedTrue() throws SQLException {
                Boolean isExecuted = connection.createStatement().execute(SqlQueries.ECOMMERCE_COUNTRIES.getQuery());
                assertThat(isExecuted, is(true));
            }


            @Test
            @Order(6)
            void executeQuery_query_returnsExpected() throws SQLException {
                ResultSet resultSet = connection.createStatement().executeQuery(SqlQueries.ECOMMERCE_COUNTRIES.getQuery());
                assertThat(resultSet, is(notNullValue()));
                // TODO: fix, check something
                //  assertThat(resultSet, is(equalTo(SqlQueries.ECOMMERCE_COUNTRIES.getExpectedResult())));
            }
        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        public class DatabaseMetaDataDependant {

            private DatabaseMetaData databaseMetaData;

            @BeforeEach
            public void beforeEach() throws SQLException {
                databaseMetaData = connection.getMetaData();
            }

            @Test
            @Order(1)
            public void databaseMetaData_isNotNull() {
                assertThat(databaseMetaData, is(notNullValue()));
            }

            @Test
            @Order(1)
            public void databaseMetaData_equalsExpected() throws SQLException {
                assertThat(databaseMetaData.getURL(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getUrl())));
                assertThat(databaseMetaData.getDatabaseProductName(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDatabaseProductName())));
                assertThat(databaseMetaData.getDatabaseProductVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDatabaseProductVersion())));
                assertThat(databaseMetaData.getDatabaseMajorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDatabaseMajorVersion())));
                assertThat(databaseMetaData.getDatabaseMinorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDatabaseMinorVersion())));

                // TODO: fix to HIKARI
                //  assertThat(databaseMetaData.getClass().getName(), is(equalTo(DataSources.HIKARI.getConnection().getDatabaseMetaData().getClassName())));
                //  This works but is misleading
                //   assertThat(databaseMetaData.getClass().getName(), is(equalTo(DataSources.Connections.DatabaseMetaDatas.ORACLE.getClassName())));

                // TODO: fix to HIKARI
                //  assertThat(databaseMetaData.unwrap(DatabaseMetaData.class).getClass().getName(), is(equalTo(DataSources.HIKARI.getConnection().getDatabaseMetaData().getDelegateClassName())));
                //  This works but is misleading
                //   assertThat(databaseMetaData.unwrap(DatabaseMetaData.class).getClass().getName(), is(equalTo(DataSources.Connections.DatabaseMetaDatas.ORACLE.getDelegateClassName())));

                assertThat(databaseMetaData.getSQLStateType(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getSqlStateValue())));

                assertThat(databaseMetaData.getDriverName(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDriverName())));
                assertThat(databaseMetaData.getDriverVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDriverVersion())));
                assertThat(databaseMetaData.getDriverMajorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDriverMajorVersion())));
                assertThat(databaseMetaData.getDriverMinorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getDriverMinorVersion())));

                assertThat(databaseMetaData.getJDBCMajorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getJdbcMajorVersion())));
                assertThat(databaseMetaData.getJDBCMinorVersion(), is(equalTo(DatabaseMetaDatas.HIKARI_PROXY.getJdbcMinorVersion())));

                assertThat(databaseMetaData.getSchemaTerm(), is(equalTo("schema")));
                assertThat(databaseMetaData.getProcedureTerm(), is(equalTo("procedure")));
                assertThat(databaseMetaData.getCatalogTerm(), is(equalTo("")));
                assertThat(databaseMetaData.getCatalogSeparator(), is(equalTo("")));

                assertThat(databaseMetaData.getIdentifierQuoteString(), is(equalTo("\"")));

                assertThat(databaseMetaData.getSQLKeywords(), is(equalTo(
                        "ACCESS, ADD, ALTER, AUDIT, CLUSTER, COLUMN, COMMENT, COMPRESS, CONNECT, DATE, DROP, " +
                                "EXCLUSIVE, FILE, IDENTIFIED, IMMEDIATE, INCREMENT, INDEX, INITIAL, INTERSECT, " +
                                "LEVEL, LOCK, LONG, MAXEXTENTS, MINUS, MODE, NOAUDIT, NOCOMPRESS, NOWAIT, NUMBER, " +
                                "OFFLINE, ONLINE, PCTFREE, PRIOR, all_PL_SQL_reserved_ words")));
                assertThat(databaseMetaData.getNumericFunctions(), is(equalTo(
                        "ABS,ACOS,ASIN,ATAN,ATAN2,CEILING,COS,EXP,FLOOR,LOG,LOG10,MOD,PI,POWER,ROUND,SIGN,SIN," +
                                "SQRT,TAN,TRUNCATE")));
                assertThat(databaseMetaData.getStringFunctions(), is(equalTo(
                        "ASCII,CHAR,CHAR_LENGTH,CHARACTER_LENGTH,CONCAT,LCASE,LENGTH,LTRIM,OCTET_LENGTH," +
                                "REPLACE,RTRIM,SOUNDEX,SUBSTRING,UCASE")));
                assertThat(databaseMetaData.getSystemFunctions(), is(equalTo("USER")));
                assertThat(databaseMetaData.getTimeDateFunctions(), is(equalTo(
                        "CURRENT_DATE,CURRENT_TIMESTAMP,CURDATE,EXTRACT,HOUR,MINUTE,MONTH,SECOND,YEAR")));
                assertThat(databaseMetaData.getSearchStringEscape(), is(equalTo("/")));
                assertThat(databaseMetaData.getExtraNameCharacters(), is(equalTo("$#")));

                assertThat(databaseMetaData.getMaxBinaryLiteralLength(), is(equalTo(1000)));
                assertThat(databaseMetaData.getMaxCharLiteralLength(), is(equalTo(2000)));
                assertThat(databaseMetaData.getMaxColumnNameLength(), is(equalTo(128)));
                assertThat(databaseMetaData.getMaxColumnsInGroupBy(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxColumnsInIndex(), is(equalTo(32)));
                assertThat(databaseMetaData.getMaxColumnsInOrderBy(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxColumnsInSelect(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxColumnsInTable(), is(equalTo(1000)));
                assertThat(databaseMetaData.getMaxConnections(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxCursorNameLength(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxIndexLength(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxSchemaNameLength(), is(equalTo(128)));
                assertThat(databaseMetaData.getMaxProcedureNameLength(), is(equalTo(128)));
                assertThat(databaseMetaData.getMaxCatalogNameLength(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxRowSize(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.doesMaxRowSizeIncludeBlobs(), is(equalTo(true)));
                assertThat(databaseMetaData.getMaxStatementLength(), is(equalTo(65535)));
                assertThat(databaseMetaData.getMaxStatements(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxTableNameLength(), is(equalTo(128)));
                assertThat(databaseMetaData.getMaxTablesInSelect(), is(equalTo(MaxLimits.UNKNOWN.getValue())));
                assertThat(databaseMetaData.getMaxUserNameLength(), is(equalTo(128)));
                assertThat(databaseMetaData.getDefaultTransactionIsolation(), is(equalTo(2)));
                assertThat(databaseMetaData.getResultSetHoldability(), is(equalTo(1)));
                assertThat(databaseMetaData.getRowIdLifetime(), is(equalTo(RowIdLifetime.ROWID_VALID_FOREVER)));


                // TODO: uncomment
                //  java.sql.SQLException: Requires GRANT SELECT ON V_$PARAMETER TO user
                //  assertThat(databaseMetaData.getMaxLogicalLobSize(), is(equalTo(0L)));

                // TODO: uncomment and figure this out
                //  All methods return ResultSet
//                assertThat(databaseMetaData.getColumns(null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getColumnPrivileges(null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getTablePrivileges(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getBestRowIdentifier(null, null, null, 0, false), is(equalTo("")));
//                assertThat(databaseMetaData.getVersionColumns(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getPrimaryKeys(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getImportedKeys(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getExportedKeys(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getCrossReference(null, null, null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getTypeInfo(), is(equalTo("")));
//                assertThat(databaseMetaData.getIndexInfo(null, null, null, false, false), is(equalTo("")));
//                assertThat(databaseMetaData.getUDTs(null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getSuperTypes(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getSuperTables(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getFunctions(null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getFunctionColumns(null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getPseudoColumns(null, null, null, null), is(equalTo("")));
//                assertThat(databaseMetaData.getClientInfoProperties(), is(equalTo("")));

                assertThat(databaseMetaData.generatedKeyAlwaysReturned(), is(equalTo(false)));

                // TODO: uncomment and figure this out
//                assertThat(databaseMetaData.getConnection(), is(equalTo("")));

                assertThat(databaseMetaData.nullsAreSortedHigh(), is(equalTo(true)));
                assertThat(databaseMetaData.storesUpperCaseIdentifiers(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsMixedCaseQuotedIdentifiers(), is(equalTo(true)));
                assertThat(databaseMetaData.storesMixedCaseQuotedIdentifiers(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsAlterTableWithAddColumn(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsColumnAliasing(), is(equalTo(true)));
                assertThat(databaseMetaData.nullPlusNonNullIsNull(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsTableCorrelationNames(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsDifferentTableCorrelationNames(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsExpressionsInOrderBy(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsOrderByUnrelated(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsGroupBy(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsGroupByUnrelated(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsGroupByBeyondSelect(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsLikeEscapeClause(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsMultipleTransactions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsNonNullableColumns(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsMinimumSQLGrammar(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsCoreSQLGrammar(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsExtendedSQLGrammar(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsANSI92EntryLevelSQL(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsIntegrityEnhancementFacility(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsOuterJoins(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsFullOuterJoins(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsLimitedOuterJoins(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSchemasInDataManipulation(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSchemasInProcedureCalls(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSchemasInTableDefinitions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSchemasInIndexDefinitions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSchemasInPrivilegeDefinitions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSelectForUpdate(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsStoredProcedures(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSubqueriesInComparisons(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSubqueriesInExists(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSubqueriesInIns(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSubqueriesInQuantifieds(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsCorrelatedSubqueries(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsUnion(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsUnionAll(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsTransactions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsDataDefinitionAndDataManipulationTransactions(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsDataManipulationTransactionsOnly(), is(equalTo(true)));
                assertThat(databaseMetaData.dataDefinitionCausesTransactionCommit(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsResultSetType(0), is(equalTo(true)));
                assertThat(databaseMetaData.supportsResultSetConcurrency(0, 0), is(equalTo(true)));
                assertThat(databaseMetaData.ownUpdatesAreVisible(0), is(equalTo(true)));
                assertThat(databaseMetaData.ownDeletesAreVisible(0), is(equalTo(true)));
                assertThat(databaseMetaData.supportsBatchUpdates(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsSavepoints(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsNamedParameters(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsGetGeneratedKeys(), is(equalTo(true)));
                assertThat(databaseMetaData.locatorsUpdateCopy(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsStatementPooling(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsStoredFunctionsUsingCallSyntax(), is(equalTo(true)));
                assertThat(databaseMetaData.supportsRefCursors(), is(equalTo(true)));

                assertThat(databaseMetaData.allProceduresAreCallable(), is(equalTo(false)));
                assertThat(databaseMetaData.allTablesAreSelectable(), is(equalTo(false)));
                assertThat(databaseMetaData.isReadOnly(), is(equalTo(false)));
                assertThat(databaseMetaData.nullsAreSortedLow(), is(equalTo(false)));
                assertThat(databaseMetaData.nullsAreSortedAtStart(), is(equalTo(false)));
                assertThat(databaseMetaData.nullsAreSortedAtEnd(), is(equalTo(false)));
                assertThat(databaseMetaData.usesLocalFiles(), is(equalTo(false)));
                assertThat(databaseMetaData.usesLocalFilePerTable(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsMixedCaseIdentifiers(), is(equalTo(false)));
                assertThat(databaseMetaData.storesLowerCaseIdentifiers(), is(equalTo(false)));
                assertThat(databaseMetaData.storesMixedCaseIdentifiers(), is(equalTo(false)));
                assertThat(databaseMetaData.storesUpperCaseQuotedIdentifiers(), is(equalTo(false)));
                assertThat(databaseMetaData.storesLowerCaseQuotedIdentifiers(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsAlterTableWithDropColumn(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsConvert(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsConvert(0, 0), is(equalTo(false)));
                assertThat(databaseMetaData.supportsMultipleResultSets(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsANSI92IntermediateSQL(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsANSI92FullSQL(), is(equalTo(false)));
                assertThat(databaseMetaData.isCatalogAtStart(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsCatalogsInDataManipulation(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsCatalogsInProcedureCalls(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsCatalogsInTableDefinitions(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsCatalogsInIndexDefinitions(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsCatalogsInPrivilegeDefinitions(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsPositionedDelete(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsPositionedUpdate(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsOpenCursorsAcrossCommit(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsOpenCursorsAcrossRollback(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsOpenStatementsAcrossCommit(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsOpenStatementsAcrossRollback(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsTransactionIsolationLevel(0), is(equalTo(false)));
                assertThat(databaseMetaData.dataDefinitionIgnoredInTransactions(), is(equalTo(false)));
                assertThat(databaseMetaData.ownInsertsAreVisible(0), is(equalTo(false)));
                assertThat(databaseMetaData.othersUpdatesAreVisible(0), is(equalTo(false)));
                assertThat(databaseMetaData.othersDeletesAreVisible(0), is(equalTo(false)));
                assertThat(databaseMetaData.othersInsertsAreVisible(0), is(equalTo(false)));
                assertThat(databaseMetaData.updatesAreDetected(0), is(equalTo(false)));
                assertThat(databaseMetaData.deletesAreDetected(0), is(equalTo(false)));
                assertThat(databaseMetaData.insertsAreDetected(0), is(equalTo(false)));
                assertThat(databaseMetaData.supportsMultipleOpenResults(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsResultSetHoldability(0), is(equalTo(false)));
                assertThat(databaseMetaData.autoCommitFailureClosesAllResultSets(), is(equalTo(false)));
                assertThat(databaseMetaData.supportsSharding(), is(equalTo(false)));
            }

            @Test
            @Order(2)
            void getTableTypes_returnsExpected() throws SQLException {
                ResultSet tableTypes = databaseMetaData.getTableTypes();
                assertThat(tableTypes, is(notNullValue()));
                // TODO: check for something useful
                //  assertThat(tableTypes.toString(), is(equalTo(DataSources.HIKARI.getConnection().getDatabaseMetaData().getTableTypes())));
            }

            @Test
            @Order(3)
            void getTables_returnsExpected() throws SQLException {
                ResultSet tables = databaseMetaData.getTables(
                        Catalogs.NULL.getName(),
                        Schemas.ECOMMERCE.getName(),
                        Tables.NULL.getName(),
                        TableTypes.NULL.getName()
                );
                assertThat(tables, is(notNullValue()));
                // TODO: check for something useful
                //  assertThat(tables.toString(), is(equalTo(DataSources.HIKARI.getConnection().getDatabaseMetaData().getTable())));
            }

            @Test
            @Order(4)
            public void getClientInfoProperties_returnsExpected() throws SQLException {
                ResultSet clientInfoProperties = databaseMetaData.getClientInfoProperties();
                assertThat(clientInfoProperties, is(notNullValue()));
                // TODO - fix, do something more
                //  fail();
            }

            @Test
            @Order(5)
            public void getAttributes_throwsSQLException_unsupportedFeature() throws SQLException {
                assertThat(
                        assertThrows(
                                SQLException.class,
                                () -> databaseMetaData.getAttributes(null, null, null, null)
                        ).getMessage(),
                        is(equalTo("Unsupported feature: "))
                );
            }

            @Nested
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
            public class SchemasDependant {

                @Test
                @Order(1)
                public void getSchemas_noArgs_returnsExpected() throws SQLException {
                    ResultSet schemas = databaseMetaData.getSchemas();
                    assertThat(schemas, is(notNullValue()));
                    // TODO - fix, do something more
                    //  fail();
                }

                @Test
                @Order(2)
                public void getSchemas_args_returnsExpected() throws SQLException {
                    ResultSet schemas = dataSource.getConnection().getMetaData().getSchemas(null, null);
                    assertThat(schemas, is(notNullValue()));
                    // TODO - fix, do something more
                    //  fail();
                }
            }

            @Nested
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
            public class CatalogsDependant {

                private ResultSet catalogs;

                @BeforeEach
                public void beforeEach() throws SQLException {
                    catalogs = databaseMetaData.getCatalogs();
                }

                @Test
                @Order(1)
                public void catalogs_isNotNull() throws SQLException {
                    assertThat(catalogs, is(notNullValue()));
                }

                @Test
                @Order(2)
                public void catalogs_equalsExpected() throws SQLException {
                    assertThat(catalogs.getType(), is(equalTo(ResultSet.TYPE_FORWARD_ONLY)));
                    assertThat(catalogs.getFetchDirection(), is(equalTo(ResultSet.FETCH_FORWARD)));
                    assertThat(catalogs.getConcurrency(), is(equalTo(ResultSet.CONCUR_READ_ONLY)));
                    assertThat(catalogs.getHoldability(), is(equalTo(ResultSet.HOLD_CURSORS_OVER_COMMIT)));
                    assertThat(catalogs.getWarnings(), is(nullValue()));
                    assertThat(catalogs.getFetchSize(), is(equalTo(10)));
                    assertThat(catalogs.getRow(), is(equalTo(0)));
                    assertThat(catalogs.isClosed(), is(equalTo(false)));
                    assertThat(catalogs.rowInserted(), is(equalTo(false)));

                    assertThat(
                            assertThrows(SQLException.class, catalogs::getCursorName).getMessage(),
                            is(equalTo("Unsupported feature: getCursorName"))
                    );

                    assertThat(
                            assertThrows(SQLException.class, catalogs::isLast).getMessage(),
                            is(equalTo("Invalid operation for forward only resultset : isLast"))
                    );

                    assertThat(
                            assertThrows(SQLException.class, () -> catalogs.getString(1)).getMessage(),
                            is(equalTo("ResultSet.next was not called"))
                    );
                    assertThat(catalogs.next(), is(equalTo(false)));
                    assertThat(
                            assertThrows(SQLException.class, () -> catalogs.getString(1)).getMessage(),
                            is(equalTo("Result set after last row"))
                    );

                    assertThat(
                            assertThrows(SQLException.class, catalogs::wasNull).getMessage(),
                            is(equalTo("No data read"))
                    );
                    // TODO - no idea why fail?
                    // fail();
                }

                @Test
                @Order(3)
                public void getMetadata_returnsExpected() throws SQLException {
                    ResultSetMetaData resultSetMetaData = catalogs.getMetaData();
                    assertThat(resultSetMetaData, is(notNullValue()));
                    assertThat(resultSetMetaData.getCatalogName(0), is(equalTo("")));
                    assertThat(resultSetMetaData.getSchemaName(0), is(equalTo("")));
                    assertThat(resultSetMetaData.getTableName(0), is(equalTo("")));

                    assertThat(resultSetMetaData.getColumnCount(), is(equalTo(1)));

                    assertThat(
                            assertThrows(SQLException.class, () -> resultSetMetaData.getColumnName(0)).getMessage(),
                            is(equalTo("Invalid column index: getValidColumnIndex"))
                    );

                    assertThat(resultSetMetaData.getColumnName(1), is(equalTo("TABLE_CAT")));
                    assertThat(resultSetMetaData.getColumnLabel(1), is(equalTo("TABLE_CAT")));
                    assertThat(resultSetMetaData.getColumnType(1), is(equalTo(1)));
                    assertThat(resultSetMetaData.getColumnTypeName(1), is(equalTo("CHAR")));
                    assertThat(resultSetMetaData.getColumnClassName(1), is(equalTo(String.class.getName())));
                    assertThat(resultSetMetaData.getColumnDisplaySize(1), is(equalTo(7)));
                    assertThat(resultSetMetaData.getPrecision(1), is(equalTo(7)));
                    assertThat(resultSetMetaData.getScale(1), is(equalTo(0)));

                    assertThat(resultSetMetaData.isAutoIncrement(1), is(equalTo(false)));
                    assertThat(resultSetMetaData.isCaseSensitive(1), is(equalTo(true)));
                    assertThat(resultSetMetaData.isCurrency(1), is(equalTo(false)));
                    assertThat(resultSetMetaData.isReadOnly(1), is(equalTo(false)));
                    assertThat(resultSetMetaData.isWritable(1), is(equalTo(true)));
                    assertThat(resultSetMetaData.isDefinitelyWritable(1), is(equalTo(false)));
                    assertThat(resultSetMetaData.isNullable(1), is(equalTo(1)));
                    assertThat(resultSetMetaData.isSearchable(1), is(equalTo(true)));
                    assertThat(resultSetMetaData.isSigned(1), is(equalTo(true)));

                    assertThat(resultSetMetaData.isWrapperFor(OracleConnection.class), is(equalTo(false)));
                }

                @Test
                @Order(4)
                public void getStatement_returnsExpected() throws SQLException {
                    Statement statement = catalogs.getStatement();
                    assertThat(statement, is(notNullValue()));
                    assertThat(statement.getWarnings(), is(nullValue()));
                    // TODO: do something more here?
                    //  fail();
                }
            }

            @Nested
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
            public class GetProceduresDependant {

                ResultSet procedures;

                @BeforeEach
                public void beforeEach() throws SQLException {
                    // TODO:
                    //  fail("never stops executing");
                    // procedures = databaseMetaData.getProcedures(null, null, null);
                }

                @Test
                @Order(1)
                public void procedures_isNotNull() throws SQLException {
                    // TODO:
                    //  fix
//                    assertThat(procedures, is(notNullValue()));
                }

                @Test
                @Order(2)
                public void procedures_equalsExpected() throws SQLException {
                    // TODO:
                    //  fix
//                    assertThat(procedures, is(equalTo("")));
                }
            }

            @Nested
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
            public class GetProcedureColumnsDependant {

                ResultSet procedureColumns;

                @BeforeEach
                public void beforeEach() throws SQLException {
                    // TODO:
                    //  fail("never stops executing");
                    procedureColumns = databaseMetaData.getProcedureColumns(null, null, null, null);
                }

                @Test
                @Order(1)
                public void procedureColumns_isNotNull() throws SQLException {
                    // TODO:
                    //  fix
//                    assertThat(procedureColumns, is(notNullValue()));
                }

                @Test
                @Order(1)
                public void procedureColumns_equalsExpected() throws SQLException {
                    // TODO: fix
//                     assertThat(procedureColumns, is(equalTo("")));
                }
            }
        }
    }
}

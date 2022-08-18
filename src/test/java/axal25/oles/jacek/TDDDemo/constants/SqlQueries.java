package axal25.oles.jacek.TDDDemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SqlQueries {
    VALIDATION_QUERY("SELECT 1 FROM DUAL", null),
    DATABASE_VALIDATION("SELECT name, open_mode FROM v$database", ""),
    ECOMMERCE_COUNTRIES("SELECT id, name, short_name FROM ecommerce.countries", null);

    private final String query;
    private final String expectedResult;
}

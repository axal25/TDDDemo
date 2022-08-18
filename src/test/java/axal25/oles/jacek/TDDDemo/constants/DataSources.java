package axal25.oles.jacek.TDDDemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public enum DataSources {
    HIKARI_DEFAULT(Pattern.compile("^HikariDataSource \\(HikariPool\\-[0-9]+\\)$")),
    HIKARI_ECOMMERCE(Pattern.compile("^HikariDataSource \\(HikariPoolEcommerce\\)$")),
    SPRING_DRIVER_MANAGER(Pattern.compile("^org\\.springframework\\.jdbc\\.datasource\\.DriverManagerDataSource@[0-9a-z]+$"));

    private final Pattern toStringPattern;
}

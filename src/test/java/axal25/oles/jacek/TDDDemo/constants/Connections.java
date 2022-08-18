package axal25.oles.jacek.TDDDemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public enum Connections {
    HIKARI_PROXY(
            Pattern.compile("^HikariProxyConnection@[0-9a-z]+ wrapping oracle\\.jdbc\\.driver\\.T4CConnection@[0-9a-z]+$")
    ),
    ORACLE_T4C(
            Pattern.compile("^oracle\\.jdbc\\.driver\\.T4CConnection@[a-z0-9]+$")
    );

    private final Pattern toStringPattern;
}

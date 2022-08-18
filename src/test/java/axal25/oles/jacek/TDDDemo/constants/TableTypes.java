package axal25.oles.jacek.TDDDemo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TableTypes {
    NULL(new String[]{null});

    private final String[] name;
}

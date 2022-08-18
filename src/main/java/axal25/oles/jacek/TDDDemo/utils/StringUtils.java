package axal25.oles.jacek.TDDDemo.utils;

public class StringUtils {

    public static int compare(String s1, String s2) {
        return s1 == null && s2 == null ? 0
                : s1 == null ? -1
                : s2 == null ? 1
                : s1.compareTo(s2);
    }
}

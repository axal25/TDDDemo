package axal25.oles.jacek.TDDDemo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BeanProvider {
    public Types type() default Types.RUN;

    public enum Types {
        TEST, RUN;
    }
}

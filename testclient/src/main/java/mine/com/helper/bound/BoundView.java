package mine.com.helper.bound;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BoundView {
    int value() default -1;

    boolean isClick() default false;
}

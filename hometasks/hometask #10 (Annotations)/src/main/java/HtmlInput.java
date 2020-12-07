import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 03.12.2020
 * 15.Annotations_SOURCE
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface HtmlInput {
    String type() default "text";
    String name() default "";
    String placeholder() default "";
}

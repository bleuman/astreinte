package net.atos.si.ma.mt.auth2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedRoles {
	String roles() default "*";

	boolean mustConnect() default false;

}

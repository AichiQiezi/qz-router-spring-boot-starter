package cn.acqz.middleware.db.router.annotation;

import java.lang.annotation.*;

/**
 * @author feng
 * @date 2023/7/28 16:25
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouterStrategy {

    boolean splitTable() default false;

}
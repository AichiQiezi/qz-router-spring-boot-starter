package cn.acqz.middleware.db.router.annotation;

import java.lang.annotation.*;

/**
 * 为切面提供切点，同时可以获取方法中入参属性的某个字段，用来做为路由字段
 * @author feng
 * @date 2023/7/27 21:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DBRouter {
    /**
     * 分库分表字段
     * @return 字段
     */
    String key() default "";

}

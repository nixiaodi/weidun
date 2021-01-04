package org.jiang.security.annocation;

import java.lang.annotation.*;

/**
 * 存在该注解的缓存方法会抛出异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}

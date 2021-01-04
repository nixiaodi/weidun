package org.jiang.security.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description Redis缓存切面，防止Redis宕机影响正常业务逻辑
 * @Author jiang
 * @Create 2021/1/4
 * @Version 1.0
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class CacheAspect {
}

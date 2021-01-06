package org.jiang.common.exception;

import org.jiang.common.api.IErrorCode;

/**
 * @Description 断言处理类，用于抛出各种API异常
 * @Author jiang
 * @Create 2021/1/4
 * @Version 1.0
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}

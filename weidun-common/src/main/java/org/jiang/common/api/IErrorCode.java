package org.jiang.common.api;

/**
 * @Description 封装API的错误码
 * @Author jiang
 * @Create 2020/12/30
 * @Version 1.0
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}

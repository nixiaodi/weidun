package org.jiang.common.exception;

import org.jiang.common.api.IErrorCode;

/**
 * @Description 自定义API异常
 * @Author jiang
 * @Create 2021/1/4
 * @Version 1.0
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message,Throwable cause) {
        super(message,cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

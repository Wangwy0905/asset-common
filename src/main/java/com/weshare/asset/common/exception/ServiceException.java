package com.weshare.asset.common.exception;


public class ServiceException extends AssetException {
    public ServiceException() {
        super();
    }

    public ServiceException(Throwable ex) {
        super(ex);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }

    public ServiceException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ServiceException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }

    public static ServiceException illegalOperation() {
        return new ServiceException("操作员信息为空，请确认是否有权限执行该操作!");
    }
}

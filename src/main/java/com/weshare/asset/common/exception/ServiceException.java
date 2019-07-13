package com.weshare.asset.common.exception;

/**
 * 业务异常
 * @author zhibin.wang
 */
public class ServiceException extends AssetException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object... args) {
        super(String.format(message, args));
    }

    public static ServiceException illegalOperation() {
        return new ServiceException("操作员信息为空，请确认是否有权限执行该操作!");
    }
}

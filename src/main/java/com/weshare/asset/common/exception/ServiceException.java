package com.weshare.asset.common.exception;


import com.weshare.asset.common.trait.StatusDescriptor;

import java.text.MessageFormat;

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
        super(MessageFormat.format(message, args));
    }
    public ServiceException(String message, Throwable ex, Object... args) {
        super(MessageFormat.format(message, args), ex);
    }

    public ServiceException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }
    public ServiceException(StatusDescriptor descriptor, Object... args) {
        super(descriptor.getStatus(), MessageFormat.format(descriptor.getMsgTemplate(), args));
    }
    public ServiceException(StatusDescriptor descriptor, Throwable ex, Object... args) {
        super(descriptor.getStatus(), MessageFormat.format(descriptor.getMsgTemplate(), args), ex);
    }

    public static ServiceException illegalOperation() {
        return new ServiceException("操作员信息为空，请确认是否有权限执行该操作!");
    }
}

package com.weshare.asset.common.exception;

import com.weshare.asset.common.trait.StatusDescriptor;

import java.text.MessageFormat;

/**
 * @author zhibin.wang
 */
public class BizException extends AssetException {
    public static final Integer BIZ_EXCEPTION_CODE = 406;
    public BizException(String message) {
        super(BIZ_EXCEPTION_CODE, message);
    }
    public BizException(Integer code, String message) {
        super(code, message);
    }
    public BizException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }
    public BizException(StatusDescriptor descriptor, Object... args) {
        super(descriptor.getStatus(), MessageFormat.format(descriptor.getMsgTemplate(), args));
    }
    public BizException(StatusDescriptor descriptor, Throwable ex, Object... args) {
        super(descriptor.getStatus(), MessageFormat.format(descriptor.getMsgTemplate(), args), ex);
    }
}

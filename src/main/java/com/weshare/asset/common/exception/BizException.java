package com.weshare.asset.common.exception;

import com.weshare.asset.common.trait.StatusDescriptor;

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
    public BizException(StatusDescriptor descriptor) {
        super(descriptor.getStatus(), descriptor.getMessage());
    }
    public BizException(StatusDescriptor descriptor, Throwable ex) {
        super(descriptor.getStatus(), descriptor.getMessage(), ex);
    }
}

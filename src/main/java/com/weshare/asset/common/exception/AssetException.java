package com.weshare.asset.common.exception;

import org.modelmapper.internal.util.Assert;

/**
 *
 * @author zhibin.wang
 */
public class AssetException extends Exception {
    protected Integer code;

    public AssetException() {
        super();
    }

    public AssetException(Throwable ex) {
        super(ex);
    }

    public AssetException(String message) {
        super(message);
    }

    public AssetException(Integer code, String message) {
        super(message);

        Assert.notNull(code, "错误类型不能为空！");
        this.code = code;
    }

    public AssetException(Integer code, String message, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}

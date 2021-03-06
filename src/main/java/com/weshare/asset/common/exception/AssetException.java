package com.weshare.asset.common.exception;

import org.springframework.util.Assert;

/**
 *
 * @author zhibin.wang
 */
public class AssetException extends Exception {
    protected Integer code = 500000;

    public AssetException() {
        super();
    }

    public AssetException(Throwable ex) {
        super(ex);
    }

    public AssetException(String message) {
        super(message);
        this.code = 500000;
    }

    public AssetException(Integer code, String message) {
        super(message);

        Assert.notNull(code, "错误类型不能为空！");
        this.code = code;
    }

    public AssetException(String message, Throwable ex) {
        super(message, ex);
    }

    public AssetException(Integer code, String message, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}

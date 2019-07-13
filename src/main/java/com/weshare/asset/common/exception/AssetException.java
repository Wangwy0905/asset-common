package com.weshare.asset.common.exception;

/**
 *
 * @author zhibin.wang
 */
public class AssetException extends Exception {
    public AssetException(String message) {
        super(message);
    }

    public AssetException(Throwable ex) {
        super(ex);
    }
}

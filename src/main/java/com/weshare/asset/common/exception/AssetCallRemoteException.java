package com.weshare.asset.common.exception;

import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.model.Response;

/**
 * 资产业务线内部系统间调用异常
 * @author zhibin.wang
 */
public class AssetCallRemoteException extends AssetException {
    public AssetCallRemoteException() {
        super(Response.BUSINESS_ERROR, "远程调用失败！");
    }

    public AssetCallRemoteException(Throwable ex) {
        super(Response.BUSINESS_ERROR, "远程调用失败！", ex);
    }

    public AssetCallRemoteException(Integer code, String message) {
        super(code, message);
    }

    public AssetCallRemoteException(String message) {
        super(Response.BUSINESS_ERROR, message);
    }

    public AssetCallRemoteException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }
}

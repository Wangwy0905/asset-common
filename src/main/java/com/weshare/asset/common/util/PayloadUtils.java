package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import com.weshare.asset.common.model.Response;
import org.modelmapper.internal.util.Assert;
import org.springframework.lang.Nullable;

/**
 * 从AssetEntity中获取Payload对象，如果status不是200，则抛AssetCallRemoteException
 * @author zhibin.wang
 */
public class PayloadUtils {
    @Nullable
    public static <P> P extract(Response response, Class<P> payload) throws ServiceException {
        Assert.notNull(response, "传入的实体对象不能为空");
        Assert.notNull(payload, "待转换类型为空");

        if (response.getStatus() != Response.SUCCESS) {
            throw new ServiceException(response.getStatus(), response.getMessage());
        }

        return ConversionUtils.convert(response.getPayload(), payload);
    }
}

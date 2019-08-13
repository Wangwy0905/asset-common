package com.weshare.umg.util;

import com.weshare.umg.exception.UmgApiException;
import com.weshare.umg.remote.UmgConfig;
import com.weshare.umg.response.base.Result;
import com.weshare.umg.system.ClientType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

    public ValidationUtils() {
    }

    public static void validateUmConfig(UmgConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("UmgConfig Cannot Be null");
        } else {
            String appCode = config.getAppCode();
            if (appCode == null || appCode.trim().length() == 0) {
                throw new IllegalArgumentException("app code Cannot Be null");
            } else {
                String token = config.getToken();
                if (token == null || token.trim().length() == 0) {
                    throw new IllegalArgumentException("token code Cannot Be null");
                } else {
                    Integer clientType = config.getClientType();
                    if (clientType == null) {
                        throw new IllegalArgumentException("remote type Cannot Be null");
                    } else {
                        ClientType type = ClientType.getByValue(clientType);
                        if (type == null) {
                            throw new IllegalArgumentException("remote type error");
                        } else {
                            String urls = config.getUmgurl();
                            if (type.equals(ClientType.HTTP) && (urls == null || urls.trim().length() == 0)) {
                                throw new IllegalArgumentException("umg urls Cannot Be null");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void validationResult(String body) throws UmgApiException {

    }

    public static void validationResult(Result result) throws UmgApiException {

    }
}
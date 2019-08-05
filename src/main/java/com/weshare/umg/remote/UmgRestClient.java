
package com.weshare.umg.remote;

import com.weshare.asset.common.util.POJOUtils;
import com.weshare.umg.exception.UmgApiException;
import com.weshare.umg.request.sms.SmsRequest;
import com.weshare.umg.response.base.Result;
import com.weshare.umg.util.Commons;
import com.weshare.umg.util.EncryptUtils;
import com.weshare.umg.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class UmgRestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmgRestClient.class);
    private String appCode;
    private String token;
    private String umgurl;
    private RestTemplate restTemplate;

    UmgRestClient(UmgRestClientBuilder builder) {
        this.umgurl = builder.getUmgConfig().getUmgurl();
        this.token = builder.getUmgConfig().getToken();
        this.appCode = builder.getUmgConfig().getAppCode();
        this.restTemplate = builder.getRestTemplate();
    }

    public Result sendSms(SmsRequest request) throws UmgApiException {
        String url = this.umgurl + "/sms/send";
        request.setAppCode(this.appCode);
        Long timestamp = System.currentTimeMillis();
        request.setTimestamp(timestamp);
        request.setSign(EncryptUtils.sha256(timestamp + this.token));
        String body = Commons.doRestPost(url, POJOUtils.toString(request), this.restTemplate);
        Result<String> result = POJOUtils.deserialize(body, Result.class);
        return result;
    }
}


package com.weshare.umg.remote;

import com.weshare.asset.common.util.POJOUtils;
import com.weshare.umg.exception.UmgApiException;
import com.weshare.umg.request.call.CallRequest;
import com.weshare.umg.request.sms.SmsRequest;
import com.weshare.umg.response.base.Result;
import com.weshare.umg.system.UrlConstant;
import com.weshare.umg.util.Commons;
import com.weshare.umg.util.EncryptUtils;
import org.springframework.web.client.RestTemplate;

public class UmgRestClient {
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

    public Result startCall(CallRequest request) throws UmgApiException {
        String url = this.umgurl + UrlConstant.UMG_CALL_START;
        request.setAppCode(appCode);
        Long timestamp = System.currentTimeMillis();
        request.setTimestamp(timestamp);
        request.setSign(EncryptUtils.sha256(timestamp + token));
        String body = Commons.doRestPost(url, POJOUtils.toString(request), restTemplate);
        Result<String> result = POJOUtils.deserialize(body, Result.class);
        return result;
    }
}

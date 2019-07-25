package com.weshare.asset.common.advice;

import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 优化异常处理机制
 * @author zhibin.wang
 */
@RestControllerAdvice
@Slf4j
public class AssetExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("参数校验错误", ex);
        // 422 表示无法处理的实体
        return new Response(422, ex.getMessage(), null);
    }


    @ExceptionHandler({AssetException.class})
    public Response assetExceptionHandler(AssetException ex) {
        if (ex.getCause() != null) {
            log.error("系统异常", ex.getCause());
        }

        return new Response(ex.getCode(), ex.getMessage(), null);
    }
}
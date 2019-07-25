package com.weshare.asset.common.advice;

import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.model.Response;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 优化异常处理机制
 * @author zhibin.wang
 */
@RestControllerAdvice
public class AssetExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        // 422 表示无法处理的实体
        return new Response(422, ex.getMessage());
    }


    @ExceptionHandler({AssetException.class})
    public Response assetExceptionHandler(AssetException ex) {
        return new Response(ex.getCode(), ex.getMessage(), null);
    }
}
package com.weshare.asset.common.advice;

import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.exception.ServiceException;
import com.weshare.asset.common.model.Response;
import com.weshare.asset.common.util.POJOUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 优化异常处理机制
 * @author zhibin.wang
 */
@RestControllerAdvice
@Slf4j
public class AssetExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public Response handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("参数校验错误", ex);
        // 422 表示无法处理的实体
        return new Response(500001, "[Assert Error]" + ex.getMessage(), null);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("参数校验错误", ex);

        List<ErrorMsg> msgs = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError error = (FieldError)objectError;
                ErrorMsg errorMsg = new ErrorMsg(error.getField(), error.getDefaultMessage());
                return errorMsg;
            }

            ErrorMsg errorMsg = new ErrorMsg(objectError.getObjectName(), objectError.getDefaultMessage());
            return errorMsg;
        }).collect(Collectors.toList());

        return new Response(500002, "[Validation Error]" + POJOUtils.toString(msgs), null);
    }

    @Data
    @AllArgsConstructor
    static class ErrorMsg {
        private String field;
        private String message;
    }

    @ExceptionHandler({ServiceException.class})
    public Response handleServiceException(ServiceException ex) {
        log.error("系统异常", ex);
        return new Response(ex.getCode(), "[System Error]" + ex.getMessage(), null);
    }

    @ExceptionHandler({DataAccessException.class})
    public Response handleSqlException(DataAccessException ex) {
        log.error("数据库处理异常", ex);
        return new Response(500003, "[System Error]" + ex.getLocalizedMessage(), null);
    }

    @ExceptionHandler({AssetException.class})
    public Response handleAssetException(AssetException ex) {
        if (ex.getCause() != null) {
            log.error("系统异常", ex.getCause());
        }

        return new Response(ex.getCode(), ex.getMessage(), null);
    }
}
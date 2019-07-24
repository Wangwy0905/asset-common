package com.weshare.asset.common.model;

import com.weshare.asset.common.util.ConversionUtils;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础扩展类，被用于接受网络请求中的入参，保存所有未知参数，但不提供相关属性的getter方法
 * @author zhibin.wang
 */
@Data
public class ExtensibleEntity {
    @JsonIgnore
    Map<String, Object> extension = new HashMap<String, Object>();

    /**
     * 该方法设计成private，保证extension属性只能在内部使用，不能通过get方法获取到
     * 该方法仅作为和Jackson对接的接口，在其他任何场合都不再使用
     * @return
     */
    @JsonAnyGetter
    private Map<String, Object> get() {
        return extension;
    }

    /**
     * 该方法设计成private，保证extension属性只能在内部使用，不能通过get方法获取到
     * 该方法仅作为和Jackson对接的接口，在其他任何场合都不再使用
     * @return
     */
    @JsonAnySetter
    private void set(String key, Object value) {
        if (setPrimitiveProperty(key, value)) {
            return ;
        }

        extension.put(key, value);
    }

    private boolean setPrimitiveProperty(String key, Object value) {
        Field field = ReflectionUtils.findField(this.getClass(), key);
        if (field == null) {
            return false;
        }

        field.setAccessible(true);
        try {
            ReflectionUtils.setField(field, this, value);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

//    public <T> T get(String nodeCode, Class<T> type){
//        Object result = getPrimitiveProperty(nodeCode);
//        if (result == null) {
//            result = getExtendProperty(nodeCode);
//        }
//
//        return ConversionUtils.convert(result, type);
//    }

//    private Object getPrimitiveProperty(String nodeCode) {
//        try {
//            Field field = ReflectionUtils.findField(this.getClass(), nodeCode);
//            field.setAccessible(true);
//
//            return ReflectionUtils.getField(field, this);
//        } catch (Throwable ex) {
//            return null;
//        }
//    }

//    private Object getExtendProperty(String nodeCode) {
//        return extension.get(nodeCode);
//    }
}
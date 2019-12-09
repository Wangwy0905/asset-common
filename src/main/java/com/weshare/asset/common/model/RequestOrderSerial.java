package com.weshare.asset.common.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 * @author ：yuchen.hu
 * @date ：2019/12/6
 * @description：
 */

@Data
public class RequestOrderSerial extends ExtensibleEntity{
    private Sort.Direction direction;
    private String property;
}

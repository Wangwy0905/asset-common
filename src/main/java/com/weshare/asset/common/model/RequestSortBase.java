package com.weshare.asset.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：yuchen.hu
 * @date ：2019/12/6
 * @description：
 */
@Data
public class RequestSortBase extends ExtensibleEntity {

    private List<RequestOrderSerial> orderList;

    @JsonIgnore
    public Sort getSort() {

        if (CollectionUtils.isEmpty(orderList)) return Sort.by(new Sort.Order(Sort.Direction.DESC, "createDateTime"));

        List<Sort.Order> sortOrderList = orderList.stream().map(item -> new Sort.Order(item.getDirection(), item.getProperty())).collect(Collectors.toList());
        return Sort.by(sortOrderList);
    }

}

package com.weshare.asset.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ：yuchen.hu
 * @date ：2019/12/6
 * @description：
 */
@Data
public class RequestPageBase extends RequestSortBase {

    @NotNull
    @Min(1)
    private Integer pageNum;
    @NotNull
    @Min(0)
    private Integer pageSize;

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(pageNum - 1, pageSize, getSort());
    }
}

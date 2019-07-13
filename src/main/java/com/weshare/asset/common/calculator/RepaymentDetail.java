package com.weshare.asset.common.calculator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：
 */
@Data
public class RepaymentDetail {
    @ApiModelProperty("期数")
    private Integer seqNo;
    @ApiModelProperty("还款日")
    private Date dueDate;
    @ApiModelProperty("剩余本金")
    private BigDecimal remainPrincipal;
    @ApiModelProperty("应还利息")
    private BigDecimal interest;
    @ApiModelProperty("应还本金")
    private BigDecimal repayPrincipal;
    @ApiModelProperty("应还总额")
    private BigDecimal repayTotal;
}

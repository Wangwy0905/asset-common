package com.weshare.asset.common.calculator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Repayment {
    @ApiModelProperty("借款总额")
    private BigDecimal loanAmount;
    @ApiModelProperty("借款期数")
    private Integer loanTerm;
    @ApiModelProperty("月利率")
    private BigDecimal monthlyInterestRate;
    @ApiModelProperty("还款详情")
    private List<RepaymentDetail> repaymentDetails;

    public Repayment(Double loanAmount, Integer loanTerm, Double monthlyInterestRate) {
        this.loanAmount = BigDecimal.valueOf(loanAmount);
        this.loanTerm = loanTerm;
        this.monthlyInterestRate = BigDecimal.valueOf(monthlyInterestRate);
        this.repaymentDetails = new ArrayList<>();
    }

    public void addRepaymentDetail(RepaymentDetail repaymentDetail) {
        if (this.repaymentDetails == null) this.repaymentDetails = new ArrayList<>();
        this.repaymentDetails.add(repaymentDetail);
    }
}
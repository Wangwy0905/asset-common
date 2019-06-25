package com.weshare.asset.common.util;

import com.weshare.asset.common.calculator.RateCalculator;
import com.weshare.asset.common.calculator.Repayment;
import com.weshare.asset.common.calculator.enums.RepayTypeEnum;
import com.weshare.asset.common.calculator.RateCalculatorFactory;
import org.springframework.util.Assert;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：费率计算
 */
public class RateUtils {

    public static Repayment calculate(Double loanAmount, Integer loanTerm, Double monthlyInterestRate, RepayTypeEnum type) throws Exception {
        Assert.isTrue(loanAmount > 0,"借款金额应大于0");
        Assert.isTrue(loanTerm > 0,"借款期数应大于0");
        Assert.isTrue(monthlyInterestRate > 0,"月利率应大于0");

        Repayment repayment = new Repayment(loanAmount, loanTerm, monthlyInterestRate);
        RateCalculator rateCalculator = RateCalculatorFactory.build(type);
        rateCalculator.calc(repayment);
        return repayment;
    }

}



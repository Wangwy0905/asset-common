package com.weshare.asset.common.calculator;

import com.weshare.asset.common.calculator.impl.DbdxCalculator;
import com.weshare.asset.common.calculator.enums.RepayTypeEnum;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：
 */
public class RateCalculatorFactory {

    public static RateCalculator build(RepayTypeEnum repayTypeEnum) throws Exception {
        switch (repayTypeEnum) {
            case DBDX:
                return new DbdxCalculator();
            default:
                throw new Exception("当前还款方式不支持");
        }
    }
}

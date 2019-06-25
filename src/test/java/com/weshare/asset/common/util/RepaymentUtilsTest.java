package com.weshare.asset.common.util;

import com.weshare.asset.common.calculator.Repayment;
import com.weshare.asset.common.calculator.enums.RepayTypeEnum;
import org.junit.Assert;
import org.junit.Test;

public class RepaymentUtilsTest {

    @Test
    public void generate() throws Exception {
        Repayment repayment = RepaymentUtils.generate(10000.0, 12, 0.013, RepayTypeEnum.DBDX);
        System.out.println(repayment);
        Assert.assertEquals(repayment.getRepaymentDetails().size(), 12);
    }

}
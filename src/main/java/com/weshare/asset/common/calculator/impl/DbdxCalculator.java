package com.weshare.asset.common.calculator.impl;

import com.weshare.asset.common.calculator.RateCalculator;
import com.weshare.asset.common.calculator.Repayment;
import com.weshare.asset.common.calculator.RepaymentDetail;
import com.weshare.asset.common.util.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：
 */
public class DbdxCalculator implements RateCalculator {


    @Override
    public Repayment calc(Repayment repayment) {
        Date firstDueDate = DateUtils.getNextMonthDate(new Date());
        return recursiveCalc(1, firstDueDate, repayment.getLoanAmount(), repayment);
    }


    /**
     * @param seqNo           期数
     * @param remainPrincipal 剩余本金
     * @param repayment
     * @return
     */
    private Repayment recursiveCalc(Integer seqNo, Date dueDate, BigDecimal remainPrincipal, Repayment repayment) {
        RepaymentDetail repaymentDetail = new RepaymentDetail();
        repaymentDetail.setSeqNo(seqNo);
        repaymentDetail.setDueDate(dueDate);
        if (seqNo > repayment.getLoanTerm()) return repayment;

        //最后一期的应还本金=倒数第二期的剩余本金
        if (seqNo == repayment.getLoanTerm()) {
            repaymentDetail.setInterest(repayment.getLoanAmount().multiply(repayment.getMonthlyInterestRate()));
            repaymentDetail.setRepayPrincipal(remainPrincipal);
            repaymentDetail.setRemainPrincipal(remainPrincipal.subtract(repaymentDetail.getRepayPrincipal()));
            repaymentDetail.setRepayTotal(repaymentDetail.getInterest().add(repaymentDetail.getRepayPrincipal()));
            repayment.addRepaymentDetail(repaymentDetail);
            return repayment;
        }

        repaymentDetail.setInterest(repayment.getLoanAmount().multiply(repayment.getMonthlyInterestRate()));
        repaymentDetail.setRepayPrincipal(repayment.getLoanAmount().divide(BigDecimal.valueOf(repayment.getLoanTerm()), 2, RoundingMode.HALF_UP));
        repaymentDetail.setRemainPrincipal(remainPrincipal.subtract(repaymentDetail.getRepayPrincipal()));
        repaymentDetail.setRepayTotal(repaymentDetail.getInterest().add(repaymentDetail.getRepayPrincipal()));
        repayment.addRepaymentDetail(repaymentDetail);
        return recursiveCalc(seqNo + 1, DateUtils.getNextMonthDate(dueDate), repaymentDetail.getRemainPrincipal(), repayment);
    }
}

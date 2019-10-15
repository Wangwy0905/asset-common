package com.weshare.asset.common.enums;

/**
 * PRE：预提交的轨迹信息，在正式调用申请接口，推进环节执行前调用
 * BEGIN：同一个环节的PRE轨迹，当调用申请接口并正常完成时，会翻转状态至BEGIN
 * FINISH：在环节正确执行完成后，提交的环节信息
 */
public enum ApplyTraceState {
    PRE, BEGIN, FINISH
}

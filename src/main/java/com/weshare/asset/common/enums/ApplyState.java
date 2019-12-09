package com.weshare.asset.common.enums;

/**
 * RUNNING 表示当前环节异步处理过程中，还未执行完成
 */
public enum ApplyState {
    PROCESS, RUNNING, PENDING, REJECT, CANCEL, FINISH, ROLLBACK,RP
}

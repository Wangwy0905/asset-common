package com.weshare.asset.common.enums;

public enum MaritalState {
    WEIHUN(10, "未婚"),
    YIHUN(20, "已婚"),
    CHUHUN(21, "初婚"),
    ZAIHUN(22, "再婚"),
    FUHUN(23, "复婚"),
    SANGOU(30, "丧偶"),
    LIHUN(40, "离婚"),
    UNKNOWN(90, "未说明的婚姻状况"),
    ;

    MaritalState(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;

    public int getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }

    public static MaritalState getMarital(int code) {
        for (MaritalState maritalState : values()) {
            if (maritalState.code == code) {
                return maritalState;
            }
        }
        return null;
    }
}

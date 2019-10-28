package com.weshare.asset.common.enums;

public enum MaritalState {
    WEIHUN("未婚"),
    YIHUN("已婚"),
    CHUHUN("初婚"),
    ZAIHUN("再婚"),
    FUHUN("复婚"),
    SANGOU("丧偶"),
    LIHUN("离婚"),
    UNKNOWN("未说明的婚姻状况"),
    ;

    MaritalState(String name) {
        this.name = name;
    }

    public static MaritalState getMarital(String maritalStateName) {
        for (MaritalState maritalState : values()) {
            if (maritalState.name.equals(maritalStateName)) {
                return maritalState;
            }
        }
        return null;
    }

    private String name;
    public String getName() {
        return this.name;
    }
}

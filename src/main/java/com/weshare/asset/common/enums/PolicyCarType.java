package com.weshare.asset.common.enums;

public enum PolicyCarType {

    SMALL_CAR_PLATE("02"),
    SMALL_NEW_ENERGY_CAR("52"),
   ;

    private String policyCarTypeName;

    private PolicyCarType(String policyCarTypeName) {
        this.policyCarTypeName = policyCarTypeName;
    }

    public String getPolicyCarTypeName() {
        return this.policyCarTypeName;
    }
}

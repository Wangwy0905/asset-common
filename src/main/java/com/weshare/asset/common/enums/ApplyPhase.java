package com.weshare.asset.common.enums;

public enum ApplyPhase {
    SA("/apply/create"),
    AUTH("/apply/auth"),
    FACE("/apply/face"),
    PBOC("/apply/pboc"),
    BI("/apply/basic-info"),
    AC("/apply/contact"),
    PC("/apply/policy-car"),
    APV("/apply/apv"),
    BC("/apply/bank-card"),
    ML("/apply/make-loan"),
    FIN("/apply/finish")
    ;

    private String serviceName;
    ApplyPhase(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }
}

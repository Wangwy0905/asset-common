package com.weshare.asset.common.enums;

public enum ApplyPhase {
    SA("/process/create"),
    AUTH("/process/auth"),
    FACE("/process/face"),
    PBOC("/process/pboc"),
    BI("/process/basic-info"),
    AC("/process/contact"),
    PC("/process/policy-car"),
    APV("/process/apv"),
    BC("/process/bank-card"),
    ML("/process/make-loan"),
    FIN("/process/finish")
    ;

    private String serviceName;
    ApplyPhase(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }
}

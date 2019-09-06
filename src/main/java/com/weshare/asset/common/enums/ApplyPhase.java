package com.weshare.asset.common.enums;

public enum ApplyPhase {
    SA("/process/create", ""),
    AUTH("/process/auth", ""),
    FACE("/process/face", ""),
    PBOC("/process/pboc", ""),
    BI("/process/basic-info", ""),
    AC("/process/add-contact", ""),
    PC("/process/policy-car", ""),
    APV("/process/apv", ""),
    BC("/process/bank-card", ""),
    ML("/process/make-loan", ""),
    FIN("/process/finish", "")
    ;

    private String serviceName;
    private String phaseName;
    ApplyPhase(String serviceName, String phaseName) {
        this.serviceName = serviceName;
        this.phaseName = phaseName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getPhaseName() {
        return this.phaseName;
    }
}

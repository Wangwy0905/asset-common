package com.weshare.asset.common.enums;

public enum ApplyPhase {
    SA("/process/create", "SA_DFT"),
    AUTH("/process/auth", "AUTH_DFT"),
    FACE("/process/face", "FACE_DFT"),
    PBOC("/process/pboc", "PBOC_DFT"),
    BI("/process/basic-info", "BI_DFT"),
    AC("/process/add-contact", "AC_DFT"),
    PC("/process/policy-car", "PC_DFT"),
    APV("/process/apv", "APV_DFT"),
    BC("/process/bank-card", "BC_DFT"),
    ML("/process/make-loan", "ML_DFT"),
    RP("/process/repayment", "RP_DFT"),
    FIN("/process/finish", "FIN_DFT"),

    MER_SA("/process/mer-create", "MER_DFT")
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
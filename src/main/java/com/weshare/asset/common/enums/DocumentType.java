package com.weshare.asset.common.enums;

/**
 * 不存在两份模板文件的类型是相同的
 */
public enum DocumentType {
    APP_REGISTRY("用户注册协议", false),
    PRIVACY_POLICY("隐私政策", false),
    COMPREHENSIVE_AUTHORIZATION("综合授权书", false),
    PBOC_QUERY_GRANT("人行授权协议", true),
    ELECTRONIC_SIGNATURE_GRANT("电子签名授权书", true),
    LOAN_CONTRACT_12("贷款合同", true),
    LOAN_CONTRACT_36("贷款合同", true),
    ENTRUSTED_WITHHOLDING("扣款协议书", true),
    ID_CARD_FRONT("身份证正面照片", false),
    ID_CARD_BACK("身份证反面照片", false),
    FACE_DETECT_IMAGE("人脸识别照片", false);

    private String name = "UNKNOWN";
    private boolean needSign = false;
    DocumentType(String name, boolean needSign) {
        this.name = name;
        this.needSign = needSign;
    }

    public String getName() {
        return this.name;
    }
    public boolean isNeedSign() {
        return this.needSign;
    }
}

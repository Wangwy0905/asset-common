package com.weshare.asset.common.enums;

/**
 * 不存在两份模板文件的类型是相同的
 */
public enum DocumentType {
    APP_REGISTRY("用户注册协议", false),
    PRIVACY_POLICY("分享贷隐私政策", false),
    PERSONAL_INFO_USE_GRANT("个人信息使用授权协议", false),
    PBOC_QUERY_GRANT("个人征信查询授权协议", true),
    PERSONAL_INFO_QUERY_GRANT("个人信息查询授权协议", true),
    LOAN_CREDIT_GRANT("信用贷款授信协议", true),
    LOAN_CONTRACT("借款合同", true),
    ELECTRONIC_SIGNATURE_GRANT("电子签名授权书", true),
    ENTRUSTED_WITHHOLDING("委托代扣协议", true),
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

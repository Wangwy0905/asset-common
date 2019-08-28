package com.weshare.asset.common.enums;

public enum Relation {
    SPOUSE("配偶"),
    FRIEND("朋友"),
    COLLEAGUE("同事"),
    PARENT("父母"),
    CHILD("子女"),
    RELATIVE("亲戚"),
    CLERK("店员"),
    LANDLORD("房东"),
    BROTHER("兄弟"),
    CLASSMATE("同学"),
    OTHERRELATIVE("其他亲属"),
    OTHER("其他"),
    BROTHERANDSISTER("兄妹"),
    SIBLING("姐弟"),
    SISTER("姐妹"),
    ONESELF("本人");


    Relation(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return this.name;
    }
    // 兄弟、同学、其他亲属、其他、兄妹、接地、姐妹、本人


}

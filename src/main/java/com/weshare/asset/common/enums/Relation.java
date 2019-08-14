package com.weshare.asset.common.enums;

public enum Relation {
    SPOUSE("配偶"),
    FRIEND("朋友"),
    COLLEAGUE("同事"),
    PARENT("父母"),
    CHILD("子女"),
    BROTHER("兄弟姐妹"),
    RELATIVE("亲戚"),
    CLERK("店员"),
    LANDLORD("房东"),
    ;

    Relation(String name) {
        this.name = name;
    }

    private String name;
    private String getName() {
        return this.name;
    }
}

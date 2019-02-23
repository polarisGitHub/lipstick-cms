package com.polaris.he.framework.entity.constanst;

/**
 * User: hexie
 * Date: 2019-01-10 21:25
 * Description:
 */
public enum CosmeticsEnum {

    LIPSTICK("lipstick", "口红");

    private String code;

    private String name;

    CosmeticsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static CosmeticsEnum getByCode(String code) {
        for (CosmeticsEnum value : CosmeticsEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
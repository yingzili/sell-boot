package com.neuedu.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum  implements CodeEnum{
    NOT_PAY(0,"等待支付"),
    PAID(1,"支付成功");
    private Integer code;
    private String message;
    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.neuedu.sell.utils;

import com.neuedu.sell.enums.CodeEnum;

public class EnumUtil {
    //通过code值返回枚举对象
    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> EnumClass) {
        for (T each : EnumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}

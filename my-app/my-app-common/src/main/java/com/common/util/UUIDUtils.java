package com.common.util;

import java.util.UUID;

public class UUIDUtils
{
    /**
     * 随机UUID 32位
     * @return
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

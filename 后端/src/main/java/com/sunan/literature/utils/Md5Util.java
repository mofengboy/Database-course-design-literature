package com.sunan.literature.utils;

import org.springframework.util.DigestUtils;

public class Md5Util {
    // 加盐
    private static final String slat = "&%5asdf$$#@";

    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}

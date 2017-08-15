package com.jim.demo.controller;

import com.jim.demo.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/11/10.
 */
public class IbenewController {

    /**
     * 生成签名
     * @param text
     * @param url
     * @param token
     * @return
     */
    public  String getSign(String text, String url, String token) {
        String sign = text + url + token;
        String ensign = null;
        try {
            ensign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String md5 = StringUtils.MD5(ensign);
        return md5;
    }
}

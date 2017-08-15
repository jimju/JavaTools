package com.jim.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/10/21.
 */
public class StringUtils {
    /**
     * md5加密
     * @param s
     * @return
     */
    public  static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * SHA加密
     * @param inputStr
     * @return
     */
    public static final String KEY_SHA = "SHA";
    public static  String  getSHA(String inputStr)
    {
        BigInteger sha =null;
//        System.out.println("=======加密前的数据:"+inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
//            System.out.println("SHA加密后:" + sha.toString(32));
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }


    //对比字符串
    public static int compare(String s1,String s2){
        int length = s1.length() < s2.length()?s1.length():s2.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) > s2.charAt(i)){
                result = 1;
                break;
            }else if (s1.charAt(i) == s2.charAt(i)){
                result = 0;
            }else {
                result = -1;
                break;
            }
        }
        if (result == 0){
            result = s1.length() - s2.length();
        }
        return result;
    }

}

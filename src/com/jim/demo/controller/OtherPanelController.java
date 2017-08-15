package com.jim.demo.controller;

import com.jim.demo.utils.StringUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/10/20.
 */
public class OtherPanelController {

    public String getCode(String text){
        StringBuilder builder = new StringBuilder("\n");
        String[] keys = text.split(",");

        builder.append(" Map<String,String> map = new HashMap<String, String>();\n");
        for (int i = 0; i < keys.length; i++) {
            builder.append("map.put(\"").append(keys[i]).append("\",").append(keys[i]).append(");\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    public String getPassword(String text){
        StringBuffer buffer = new StringBuffer("\n");
        buffer.append("      MD5加密（大写）:   ").append(StringUtils.MD5(text)).append("\n      MD5加密（小写）:   ")
                .append(StringUtils.MD5(text).toLowerCase()).append("\n\n");
        try {
            buffer.append("      BASE64 加密:   ").append(StringUtils.encryptBASE64(text.getBytes())).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append("      SHA加密:   ").append(StringUtils.getSHA(text)).append("\n\n");

        return buffer.toString();
    }

    /**
     * 键值对转成Url
     * @param text
     * @return
     */
    public String changeUrl(String text){
        String nText = text.trim().replaceAll("\"","").substring(0,text.length()-2).trim();
        String[] texts = nText.split(";");

        StringBuilder builder = new StringBuilder("?");
        for (int i = 0; i < texts.length; i++) {
            String t = texts[i];
            String t1 = t.substring(t.indexOf("(")+1,t.lastIndexOf(")")).replace(",","=").replace(" ","");
            texts[i] = t1;
        }
        Arrays.sort(texts, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return o1.charAt(0) - o2.charAt(0);
            }
        });
        for (int i = 0; i < texts.length; i++) {
            builder.append(texts[i]).append("&");
        }
        builder.deleteCharAt(builder.length()-1);
       return builder.toString();
    }

    public String map2Url(String text){
        String[] texts = text.split(";");
        StringBuilder builder = new StringBuilder("String urlconn = \"?");
        for (int i = 0; i < texts.length; i++) {
            String t = texts[i].trim();
            String t1 = t.substring(t.indexOf("(")+1,t.lastIndexOf(")"));
            t1 = t1.replace(" ","");
            String[] ts = t1.split(",");
            String t2 = ts[0].replaceAll("\"","");
            String t3 = ts[1];
            StringBuilder sb = new StringBuilder(t2).append("=");
            if (t3.contains("\"")){
                sb.append(t3.replaceAll("\"",""));
            }else {
                sb.append("\" + " + t3.replaceAll("\"","") + " + \"");
            }
            sb.append("&");
            texts[i] = sb.toString();
        }

        Arrays.sort(texts, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return StringUtils.compare(o1, o2);
            }
        });
        for (int i = 0; i < texts.length; i++) {
            builder.append(texts[i]);
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("\";");
        builder.append("\naddToken(urlconn,UrlConstant.O,HttpMethod.GET,params);");
        return builder.toString();
    }

    public String Url2Map(String url){
        if (url.charAt(0) == '?'){
            url = url.substring(1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\nMap<String,String> map = new HashMap<>();\n");
        String[] kvs = url.split("&");
        for (int i = 0; i < kvs.length; i++) {
            try {
                String[] cache = kvs[i].split("=");
                builder.append("map.put(\"").append(cache[0]).append("\",\"").append(cache[1]).append("\");\n");
            }catch (Exception e){
                builder.append("\n\n数据异常 " + e.toString() +"\n\n");
            }

        }
        return builder.toString();
    }

    public String Url2Map1(String url){
        if (url.charAt(0) == '?'){
            url = url.substring(1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\nMap<String,String> map = new TreeMap<>(BNHelper.getComparator());\n");
        String[] kvs = url.split("&");
        for (int i = 0; i < kvs.length; i++) {
            try {
                String[] cache = kvs[i].split("=");
                builder.append("map.put(\"").append(cache[0]).append("\",\"").append(cache[1]).append("\");\n");
            }catch (Exception e){
                builder.append("\n\n数据异常 " + e.toString() +"\n\n");
            }
        }
        return builder.toString();
    }


    public String setTextCode(String text){
        String[] texts = text.split(",");
        StringBuilder builder = new StringBuilder("\n");
        for (int i = 0; i < texts.length; i++) {
            String tText = String.format("setTextView(R.id.%s,\"\"); //注释", "" +texts[i]);
            builder.append("\n").append(tText);
        }
        return builder.toString();
    }
}

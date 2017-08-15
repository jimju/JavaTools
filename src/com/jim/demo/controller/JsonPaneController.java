package com.jim.demo.controller;

import com.jim.demo.json.JSONArray;
import com.jim.demo.json.JSONObject;
import com.jim.demo.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class JsonPaneController {

    /**
     * @param tag  private or public
     * @param name key name
     * @param o    数据类型
     * @return
     */
    public String codeLine(String tag, String name, Object o) {
        StringBuilder builder = new StringBuilder("    ");
        builder.append(tag).append(" ").append(ObjectUtils.objectType(o)).append(" ").append(name).append(";");
        return builder.toString();
    }

    /**
     * @param name key name
     * @param o    数据类型
     * @return
     */
    public String sqlLine( String name, Object o) {
        StringBuilder builder = new StringBuilder("    ");
        String type = ObjectUtils.objectSQLType(o);
        if (type != null)
            builder.append(" `").append(name).append("` ").append(type).append(" ,");
        return builder.toString();
    }

    public String transSQL(String json,String tableName){
        StringBuilder builder = new StringBuilder(" CREATE TABLE `").append(tableName).append("` (\n");
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<String> list = new ArrayList<String>(jsonObject.keySet());
            Collections.sort(list);
            for (String key : list) {
                builder.append(sqlLine(key, jsonObject.get(key))).append("\n");
            }
        } catch (Exception e) {
            builder.append("Json数据错误");
        }
        builder.deleteCharAt(builder.length()-2); //去除最后的逗号
        builder.append(" ) ENGINE = InnoDB DEFAULT  CHARSET=utf8;");
        return builder.toString();
    }

    /**
     * 插入数据
     * @param json
     * @param tableName
     * @return
     */
    public String transInsert(String json,String tableName){
        StringBuilder builder = new StringBuilder(" INSERT INTO  `").append(tableName).append("` (\n");
        try {
            JSONArray array = new JSONArray(json);
            JSONObject cobj1 = array.getJSONObject(0);
            List<String> list = new ArrayList<String>(cobj1.keySet());
            Collections.sort(list);
            //字段行
            for (String key : list) {
                builder.append(" `").append(key).append("`,");
            }
            builder.deleteCharAt(builder.length()-1).append(")  VALUES  \n");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jobj = array.getJSONObject(i);
                List<String> list1 = new ArrayList<String>(cobj1.keySet());
                Collections.sort(list1);
                builder.append(" (");
                for (String key : list) {
                    Object oo = null;
                    try {
                        oo = jobj.get(key);
                    }catch (Exception e) {}
                    String value  = ObjectUtils.jsonObjectValues(oo,jobj,key);
                    builder.append(value);
                }
                builder.deleteCharAt(builder.length()-2).append("),\n");
            }
        }catch (Exception e) {
            builder.append("Json数据错误");
        }
        return builder.substring(0,builder.length()-2) + ";";
//        return builder.toString();
    }


    public String transJson(String tag, String json, String name) {
        StringBuilder builder = new StringBuilder("public class ").append(name).append("{\n");
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<String> list = new ArrayList<String>(jsonObject.keySet());
            Collections.sort(list);
            for (String key : list) {
                builder.append(codeLine(tag, key, jsonObject.get(key))).append("\n");
            }
        } catch (Exception e) {
            builder.append("Json数据错误");
        }
        builder.append("}");
        return builder.toString();
    }

    public String compareJson(String json1, String json2) {
        StringBuilder builder = new StringBuilder();
        JSONObject jo1 = new JSONObject(json1);
        JSONObject jo2 = new JSONObject(json2);
        List<String> list1 = new ArrayList<String>(jo1.keySet());
        List<String> list2 = new ArrayList<String>(jo2.keySet());
        List<String> cache = new ArrayList<String>();
        builder.append("左边Json有，右边没有的字段: \n");
//        builder.append(key1 + " : " + jo1.get(key1) + "\n");
        for (int i = 0; i < list1.size(); i++) {
            String key1 = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                if (key1.equals(list2.get(j))) {
                    cache.add(key1);
                    break;
                }
            }
        }
        list1.removeAll(cache);
        list2.removeAll(cache);
        for (int i = 0; i < list1.size(); i++) {
            String key1 = list1.get(i);
            builder.append(key1 + " : " + cutText(jo1, key1) + "\n");
        }

        builder.append("\n 右边有，左边没有的字段: \n");
        for (int i = 0; i < list2.size(); i++) {
            String key2 = list2.get(i);
            builder.append(key2 + " : " + cutText(jo2, key2) + "\n");
        }

        builder.append("\n 相同的字段: \n");
        for (int i = 0; i < cache.size(); i++) {
            String key3 = cache.get(i);
            builder.append(key3 + " : " + cutText(jo1, key3) + "     " + cutText(jo2, key3) + "\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * 不显示列表和Object的内容
     */
    public String cutText(JSONObject jo, String key) {
        String str = jo.get(key).toString();
        if (jo.get(key) instanceof JSONArray || jo.get(key) instanceof JSONObject) {
            return "省略内容";
        }
        return str;
    }


    //产品图片
    public String other(String json){
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject daj = jsonObject.getJSONObject("data");
        JSONArray daa = daj.getJSONArray("data");
        JSONArray ja = new JSONArray();
        for (int i = 0; i < daa.length(); i++) {
            try{
                JSONArray cja = daa.getJSONObject(i).getJSONArray("fileProperties");
                sb.append("UPDATE `think_files` SET `attachId`=").append(daa.getJSONObject(i).getInt("productHeaderId")).append(" WHERE `documentId` IN (");
                for (int j = 0; j < cja.length(); j++) {
                    ja.put(cja.getJSONObject(j));
                    sb.append(cja.getJSONObject(j).getInt("documentId")).append(",");
                }
                sb.deleteCharAt(sb.length()-1).append(");\n");
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

        return sb.toString();
    }


    public String other1(String json,String data){

        StringBuilder builder = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<String> list = new ArrayList<String>(jsonObject.keySet());
            Collections.sort(list);
            for (String key : list) {
                builder.append("$").append(data).append("[\'").append(key).append("\'] = $json -> ").append(key).append(";\n");
            }
        } catch (Exception e) {
            builder.append("Json数据错误");
        }

        return builder.toString();
    }
}

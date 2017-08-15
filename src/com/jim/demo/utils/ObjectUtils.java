package com.jim.demo.utils;

import com.jim.demo.json.JSONArray;
import com.jim.demo.json.JSONObject;

/**
 * Created by Administrator on 2016/10/21.
 */
public class ObjectUtils {
    public static String objectType(Object o){
        String text = "";
        if (o instanceof Integer){
            text = "int";
        }else if (o instanceof Double){
            text = "double";
        }else if (o instanceof Long){
            text = "long";
        }else if (o instanceof JSONArray){
            text = "List<Object>";
        }else if (o instanceof JSONObject){
            text = "Object";
        } else {
            text = "String";
        }
        return text;
    }

    public static String jsonObjectValues(Object o,JSONObject jo,String key){
        String text = "";
        StringBuilder builder = new StringBuilder();
        try {
        if (o instanceof Integer){
            text = String.valueOf(jo.getInt(key));
            builder.append(text).append(",");
        }else if (o instanceof Double){
            text = String.valueOf(jo.getDouble(key));
            builder.append(text).append(",");
        }else if (o instanceof Long){
            text = String.valueOf(jo.getLong(key));
            builder.append(text).append(",");
        }else if (o instanceof JSONArray){
            text = " -2";
            builder.append(text).append(",");
        }else if (o instanceof JSONObject){
            text = " -1";
            builder.append(text).append(",");
        } else {

                text = jo.getString(key);
                builder.append(" '").append(text).append("', ");
            }
        }catch (Exception e){
            builder.append(" '").append(text).append("', ");
        }
        return builder.toString();
    }

    public static String objectSQLType(Object o){
        String text = null;
        if (o instanceof Integer){
            text = "INT(10)";
        }else if (o instanceof Double){
            text = "DOUBLE(10.2)";
        }else if (o instanceof Long){
            text = "TEXT";

        }else if (o instanceof String){
            text = "VARCHAR(100)";
        } else if (o instanceof JSONArray){
            text = "INTEGER";
        }else if (o instanceof JSONObject){
            text = "INTEGER";
        }else {
            text = "VARCHAR(50)";
        }
        return text;
    }


}

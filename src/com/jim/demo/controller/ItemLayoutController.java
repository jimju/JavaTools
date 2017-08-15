package com.jim.demo.controller;

import com.jim.demo.bean.CodeResult;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ItemLayoutController {

    public CodeResult productSingleLineInfo(String[] tags,String[] ids) {
        CodeResult result = new CodeResult();
        StringBuilder builder = new StringBuilder();
        String h1 = " <ScrollView\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"match_parent\">\n" +
                "\n" +
                "        <LinearLayout\n" +
                "            android:layout_width=\"match_parent\"\n" +
                "            android:layout_height=\"match_parent\"\n" +
                "            android:divider=\"@android:drawable/divider_horizontal_dark\"\n" +
                "            android:showDividers=\"middle\"\n" +
                "            android:orientation=\"vertical\">\n";
        builder.append(h1);
        for (int i = 0; i < tags.length; i++) {
            String cache = "  <RelativeLayout\n" +
                    "                android:layout_width=\"match_parent\"\n" +
                    "                android:layout_height=\"wrap_content\"\n" +
                    "                android:padding=\"10dp\"\n" +
                    "                android:background=\"@android:color/white\">\n" +
                    "                <TextView\n" +
                    "                    android:id=\"@+id/auto_make_tv"+ i +"\"\n" +
                    "                    android:layout_width=\"wrap_content\"\n" +
                    "                    android:layout_height=\"wrap_content\"\n" +
                    "                    android:text=\"" +tags[i]+ "\"/>\n" +
                    "\n" +
                    "            </RelativeLayout>\n";
            builder.append(cache);
        }
        String f1 = "</LinearLayout>\n" +
                "\n" +
                "    </ScrollView>";
        builder.append(f1);

        result.setResult1(builder.toString());
        return result;
    }


    public CodeResult productSingleLineInput(String[] tags,String[] ids) {
        CodeResult result = new CodeResult();
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        String h1 = " <ScrollView\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"match_parent\">\n" +
                "\n" +
                "        <LinearLayout\n" +
                "            android:layout_width=\"match_parent\"\n" +
                "            android:layout_height=\"match_parent\"\n" +
                "            android:divider=\"@android:drawable/divider_horizontal_dark\"\n" +
                "            android:showDividers=\"middle\"\n" +
                "            android:orientation=\"vertical\">\n";
        builder.append(h1);
        for (int i = 0; i < tags.length; i++) {
            String cache = " <RelativeLayout\n" +
                    "                android:layout_width=\"match_parent\"\n" +
                    "                android:layout_height=\"wrap_content\"\n" +
                    "                android:background=\"@android:color/white\"\n" +
                    "                android:padding=\"10dp\">\n" +
                    "\n" +
                    "                <TextView\n" +
                    "                    android:id=\"@+id/tv_"+ids[i]+"\"\n" +
                    "                    android:layout_width=\"wrap_content\"\n" +
                    "                    android:layout_height=\"wrap_content\"\n" +
                    "                    android:text=\""+ tags[i] +"\" />\n" +
                    "                <EditText\n" +
                    "                    android:id=\"@+id/et_"+ids[i]+"\"\n" +
                    "                    android:layout_width=\"match_parent\"\n" +
                    "                    android:layout_height=\"match_parent\"\n" +
                    "                    android:layout_toRightOf=\"@+id/tv_"+ids[i]+"\"\n" +
                    "                    android:layout_marginLeft=\"3dp\"\n" +
                    "                     android:textSize=\"14sp\"\n" +
                    "                    android:hint=\"请输入"+tags[i]+"\"" +
                    "                    android:background=\"@android:color/transparent\"/>\n" +
                    "                <View\n" +
                    "                    android:layout_width=\"match_parent\"\n" +
                    "                    android:layout_height=\"1dp\"\n" +
                    "                    android:layout_toRightOf=\"@+id/tv_"+ids[i]+"\"\n" +
                    "                    android:layout_below=\"@id/et_"+ids[i]+"\"\n" +
                    "                    android:layout_marginLeft=\"3dp\"\n" +
                    "                    android:background=\"@android:color/darker_gray\"/>\n" +
                    "\n" +
                    "            </RelativeLayout>";
            String name = "mEt" + formatName(ids[i],"_");
            builder1.append("@BindView(R.id.et_").append(ids[i]).append(")\nEditText ").append(name).append(";\n\n");
            builder.append(cache);
        }
        String f1 = "</LinearLayout>\n" +
                "\n" +
                "    </ScrollView>";
        builder.append(f1);
        result.setResult1(builder.toString());
        result.setResult2(builder1.toString());
        /* @BindView(R.id.fl_main_top)
    FrameLayout mFrameTop;*/
        return result;
    }


    public CodeResult productSingleLineInfo1(String[] tags,String[] ids) {
        CodeResult result = new CodeResult();
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        String h1 = " <ScrollView\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"match_parent\">\n" +
                "\n" +
                "        <LinearLayout\n" +
                "            android:layout_width=\"match_parent\"\n" +
                "            android:layout_height=\"match_parent\"\n" +
                "            android:orientation=\"vertical\">\n";
        builder.append(h1);
        for (int i = 0; i < tags.length; i++) {
            String cache = "<RelativeLayout\n" +
                    "                android:id=\"@+id/rl_"+ids[i]+"\"\n" +
                    "                android:layout_width=\"match_parent\"\n" +
                    "                android:layout_height=\"wrap_content\"\n" +
                    "                android:paddingLeft=\"10dp\"\n" +
                    "                android:paddingRight=\"10dp\"\n" +
                    "                android:paddingTop=\"13dp\"\n" +
                    "                android:paddingBottom=\"13dp\"" +
                    "                android:background=\"@color/white\">\n" +
                    "\n" +
                    "                <TextView\n" +
                    "                    android:layout_width=\"wrap_content\"\n" +
                    "                    android:layout_height=\"wrap_content\"\n" +
                    "                    android:layout_centerVertical=\"true\"\n" +
                    "                    android:textColor=\"@color/text_black\"\n" +
                    "                    android:textSize=\"16sp\"\n" +
                    "                    android:text=\""+tags[i]+"\"/>\n" +
                    "\n" +
                    "                <TextView\n" +
                    "                    android:id=\"@+id/tv_"+ids[i]+"\"\n" +
                    "                    android:layout_width=\"wrap_content\"\n" +
                    "                    android:layout_height=\"wrap_content\"\n" +
                    "                    android:layout_marginRight=\"20dp\"\n" +
                    "                    android:text=\""+tags[i]+"\"\n" +
                    "                    android:textSize=\"12sp\"\n" +
                    "                    android:layout_centerVertical=\"true\"\n" +
                    "                    android:textColor=\"@color/text_common\"\n" +
                    "                    android:layout_alignParentRight=\"true\"/>\n" +
                    "\n" +
                    "                <ImageView\n" +
                    "                    android:layout_width=\"16dp\"\n" +
                    "                    android:layout_height=\"16dp\"\n" +
                    "                    android:layout_alignParentRight=\"true\"\n" +
                    "                    android:layout_centerVertical=\"true\"\n" +
                    "                    android:scaleType=\"fitCenter\"\n" +
                    "                    android:src=\"@mipmap/icon_xiaojiantou\"/>\n" +
                    "            </RelativeLayout>\n" +
                    "\n" +
                    "            <View\n" +
                    "                android:layout_width=\"match_parent\"\n" +
                    "                android:layout_height=\"1dp\"\n" +
                    "                android:background=\"@color/divider\"\n" +
                    "                android:layout_marginLeft=\"13dp\"/>\n\n";
            String name = "mTv" + formatName(ids[i],"_");
            builder1.append("@BindView(R.id.tv_").append(ids[i]).append(")\nTextView ").append(name).append(";\n\n");
            builder.append(cache);
        }
        String f1 = "</LinearLayout>\n" +
                "\n" +
                "    </ScrollView>";
        builder.append(f1);
        result.setResult1(builder.toString());
        result.setResult2(builder1.toString());
        /* @BindView(R.id.fl_main_top)
    FrameLayout mFrameTop;*/
        return result;
    }


    private String formatName(String text,String split){
        StringBuilder name = new StringBuilder();
        String[] flags = text.split(split);
        for (int i = 0; i < flags.length; i++) {
            String tName = Character.toUpperCase(flags[i].charAt(0)) + flags[i].substring(1);
            name.append(tName);
        }
        return name.toString();
    }
}
